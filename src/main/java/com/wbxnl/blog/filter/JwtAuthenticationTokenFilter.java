package com.wbxnl.blog.filter;

import com.wbxnl.blog.constant.params.HeaderParamConstant;
import com.wbxnl.blog.constant.keys.UserPrefix;
import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.exception.BlogException;
import com.wbxnl.blog.model.dto.UserDetailsDto;
import com.wbxnl.blog.model.dto.UserDto;
import com.wbxnl.blog.utils.JwtUtil2;
import com.wbxnl.blog.utils.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.Objects;

/**
 * @author wansheng
 * @createDate 2022/8/27 5:31
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws BlogException, ServletException, IOException {
        //获取token
        String token = request.getHeader(HeaderParamConstant.HEADER_TOKEN);
        if (StringUtils.hasText(token)) {
            //解析token
            //检测token有效性
            if (!JwtUtil2.checkToken(token)) {
//                throw new BlogException(OperationStateCode.TOKEN_EXPIRE);
                log.warn("Token:{}",token);
                throw new AccessDeniedException("Token已过期！");
            } else {
                String username = null;
                try {
                    username = JwtUtil2.getUserId(token);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BlogException(OperationStateCode.TOKEN_ILLEGAL);
                }
                //从redis中获取用户信息
                String redisKey = UserPrefix.USER_INFO + username;
                UserDto userDto = redisUtils.getCacheObject(redisKey);
                if (Objects.isNull(userDto)) {
                    throw new BlogException(OperationStateCode.NO_LOGIN);
                }
                UserDetailsDto userDetailsDto = new UserDetailsDto(userDto);
//                Collection<? extends GrantedAuthority> authorities = userDetailsDto.getAuthorities();
                // 存入SecurityContextHolder
                // 获取权限信息封装到Authentication中
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetailsDto, null, userDetailsDto.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        //放行
        filterChain.doFilter(request, response);
//        try {
//            filterChain.doFilter(request, response);
//        }catch (AccessDeniedException e){
//            resolver.resolveException(request, response, null, e);
//        }
//        catch (Exception e){
//            resolver.resolveException(request, response, null, new AccessDeniedException("权限不足，操作已被拒绝。"));
////            resolver.resolveException(request, response, null, new BlogException(40000,"权限不足，操作已被拒绝。"));
//        }

    }
}
