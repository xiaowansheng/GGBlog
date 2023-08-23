package com.wbxnl.blog.utils;

import com.wbxnl.blog.model.dto.RoleDto;
import com.wbxnl.blog.model.dto.UserDetailsDto;
import com.wbxnl.blog.model.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
public class SecurityUtils {

    /**
     * 获取登用户角色权限标识集合
     *
     * @return
     */
    public static List<String> getRoleStrList() {
        return getUserDto().getRoles()
                .stream()
                .map(roleDto -> roleDto.getName())
                .collect(Collectors.toList());
    }

    /**
     * 获取当前用户的角色信息列表
     * @return
     */
    public static List<RoleDto> getRoleDtoList(){
        return getUserDto().getRoles();
    }

    /**
     * 获取登用户信息id
     *
     * @return
     */
    public static String getUsername() {
        return getUserDto().getUsername();
    }

    /**
     * 获取用户账户
     *
     * @return
     */
    public static Integer getUserAuthId() {
        return getUserDto().getUserAuthId();
    }

    /**
     * 获取登用户信息id
     *
     * @return
     */
    public static Integer getUserInfoId() {
        return getUserDto().getUserInfoId();
    }

    /**
     * 获取登用户信息id
     *
     * @return
     */
    public static UserDto getUserDto() {
        return getUserDetailsDto().getUserDto();
    }


    /**
     * 获取用户详情
     **/
    public static UserDetailsDto getUserDetailsDto() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsDto) {
            return (UserDetailsDto) principal;
        }
        return null;
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}