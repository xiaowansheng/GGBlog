package com.wbxnl.blog.filter;

import com.wbxnl.blog.constant.AccessFlagConstant;
import com.wbxnl.blog.model.dto.ResourceRoleDto;
import com.wbxnl.blog.service.SystemResourceService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Author xiaowansheng
 * @Date 2023/8/20 18:13
 */
@Slf4j
@Component
    public class AuthorizationManagerImpl implements AuthorizationManager<RequestAuthorizationContext> {

    /**
     * 资源角色列表
     */
    private static List<ResourceRoleDto> resourceRoleList;

    /**
     * TODO 可以抽取为service
     */
    @Autowired
    private SystemResourceService systemResourceService;

    /**
     * 加载资源角色信息
     */
    @PostConstruct
    private synchronized void loadDataSource() {
        // 双标志检查
        if (!CollectionUtils.isEmpty(resourceRoleList)) {
            return;
        }
        resourceRoleList = systemResourceService.getResourceRoles();
        log.info("更新了最新的权限信息。");
    }

    /**
     * // 资源和角色变化时，重新加载
     * 清空接口角色信息
     */
    public void clearDataSource() {
        // 清理旧的数据
        resourceRoleList = null;
        log.info("清理了旧的权限信息，等待更新。");
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext authorizationContext) {
        HttpServletRequest request = authorizationContext.getRequest();
//        log.info("request:{},{}", request.getMethod(), request.getRequestURI());
//        跳过授权，对所有请求的地址检测都通过
//        return new AuthorizationDecision(true);
//        执行授权
        return myCheck(authentication, request);
    }


    /**
     * 把接口所需角色，和当前用户的角色 进行匹配，看用户角色是否匹配
     * 匹配则通过
     * 不匹配，校验失败
     *
     * @param authentication 使用Supplier<Authentication> 延迟Authentication查找。它不需要为每个请求查找身份验证，而是仅在授权决策需要身份验证的请求中查找身份验证
     * @param rolesOfNeed
     * @return
     */
    public AuthorizationDecision match(Supplier<Authentication> authentication, List<String> rolesOfNeed) {
        if (CollectionUtils.isEmpty(rolesOfNeed)) {
//            return new AuthorizationDecision(false);
            //如果可以访问的角色列表为空，则所有角色不允许访问
            throw new AccessDeniedException("该资源未设置访问权限，暂不允许访问！");
        }
        // 获取用户权限列表
        List<String> permissionRoleList = authentication.get().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        //查询用户角色是否和访问资源所需的角色匹配
        for (String item : rolesOfNeed) {
            //匹配放行
            if (permissionRoleList.contains(item)) {
                return new AuthorizationDecision(true);
            }
        }
        //不匹配则拒绝访问
//        return new AuthorizationDecision(false);
        throw new AccessDeniedException("您没有操作权限哦！");

    }

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext authorizationContext) {
        AuthorizationDecision decision = check(authentication, authorizationContext);
        if (decision != null && !decision.isGranted()) {
            throw new AccessDeniedException("没有操作权限");
        }
    }


    /**
     * 给用户授权接口访问权限
     *
     * @param authentication
     * @param request
     * @return
     */
    public AuthorizationDecision myCheck(Supplier<Authentication> authentication, HttpServletRequest request) {
        // 修改接口角色关系后重新加载
        if (CollectionUtils.isEmpty(resourceRoleList)) {
            this.loadDataSource();
        }
        // 获取用户请求方式
        String method = request.getMethod();
        // 获取用户请求Url
        String url = request.getRequestURI();
        //跳过登录接口的拦截
        if (url.endsWith("/login") || url.endsWith("/logout")) {
            return new AuthorizationDecision(true);
        }
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 获取接口角色信息，若为匿名接口则放行，若无对应角色则禁止
        for (ResourceRoleDto resourceRoleDto : resourceRoleList) {
            //比较路径是否匹配，再比较请求方法是否匹配
            if (antPathMatcher.match(resourceRoleDto.getPath(), url) && resourceRoleDto.getRequestMethod().equalsIgnoreCase(method)) {
                //如果是开放接口，则通过验证
                if (resourceRoleDto.getOpen() == 1) {
                    return new AuthorizationDecision(true);
                }
                //不是开放接口则获取该接口所需要的角色列表
                List<String> roleList = resourceRoleDto.getRoleList();
                return match(authentication, roleList);
//                return SecurityConfig.createList(roleList.toArray(new String[]{}));
            }
        }
        if(!CollectionUtils.isEmpty(resourceRoleList)){
            throw new AccessDeniedException("该资源未设置访问权限，暂不允许访问！");
//                throw new IllegalArgumentException("该资源未设置访问权限，暂不允许访问！");
        }
        //该资源暂未设置访问权限
//        throw new IllegalArgumentException("该资源未设置访问权限，暂不允许访问！");
        throw new AccessDeniedException("接口设置暂未开启，所有请求不允许访问！");
//        return SecurityConfig.createList(AccessFlagConstant.NO_SET);
//        return new AuthorizationDecision(false);
    }
}
