package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.exception.BlogException;
import com.wbxnl.blog.model.dto.*;
import com.wbxnl.blog.model.entity.UserAuth;
import com.wbxnl.blog.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wansheng
 * @createDate 2022/8/27 5:52
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, BlogException {
        if (username == null) {
            throw new BlogException(OperationStateCode.PARAMS_ERROR);
        }
        //查询用户账号
        UserAuth userAuth = userAuthService.lambdaQuery()
                .select(UserAuth::getId, UserAuth::getUsername, UserAuth::getUserInfoId, UserAuth::getPassword, UserAuth::getDisable, UserAuth::getLoginType)
                .eq(UserAuth::getUsername, username)
                .one();
        if (userAuth == null) {
            throw new BlogException(OperationStateCode.NULL_USER);
        }
        if (userAuth.getDisable() == 1) {
            throw new BlogException(OperationStateCode.USER_DISABLE);
        }
        return convertUserDetail(userAuth);
    }

    /**
     * 封装数据为UserDetails对象
     *
     * @param userAuth
     * @return
     */
    public UserDetailsDto convertUserDetail(UserAuth userAuth) {
        //获取用户信息
//        UserInfo userInfo = userInfoService.lambdaQuery().eq(UserInfo::getId, userAuth.getUserInfoId()).one();
        //获取用户对应角色的列表
        UserRoleDto userRoleDto = userRoleService.getUserRoles(userAuth.getId());
        if (userRoleDto == null) {
            log.info("当前用户没有角色信息！");
            // 没有角色信息即不可用，任何接口和菜单都不可操作
        } else {
            // 过滤被禁用的角色
            List<RoleDto> roleDtos = userRoleDto.getRolesList()
                    .stream()
                    .filter(roleDto -> roleDto.getDisable() == 0)
                    .collect(Collectors.toList());
            userRoleDto.setRolesList(roleDtos);
            // 如果用户的角色信息都被禁用，则也代表用户被禁用
            if (CollectionUtils.isEmpty(roleDtos)) {
                throw new BlogException(OperationStateCode.USER_DISABLE);
            }
        }
        //先存userAuth信息
        UserDto userDto = UserDto.builder()
                .userAuthId(userAuth.getId())
                .userInfoId(userAuth.getUserInfoId())
                .username(userAuth.getUsername())
                .password(userAuth.getPassword())
                .loginType(userAuth.getLoginType())
                .build();
        // 用户可能没有角色信息
        if (Objects.nonNull(userRoleDto)) {
            // 有角色信息
            userDto.setRoles(userRoleDto.getRolesList());
        }else {
            // 没有角色信息
            userDto.setRoles(new ArrayList<>(0));
        }
        return UserDetailsDto.builder()
                .userDto(userDto)
                .build();
    }
}
