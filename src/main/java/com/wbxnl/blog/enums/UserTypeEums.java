package com.wbxnl.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wansheng
 * @createDate 2023/2/23 0:47
 */
@AllArgsConstructor
@Getter
public enum UserTypeEums {
    /**
     * 登录用户
     */
    User("user","用户"),
    /**
     * 游客
     */
    Visitor("visitor","游客"),
    /**
     * 匿名
     */
    Anonymous("anonymous","匿名");

    /**
     * 用户名称
     */
    private final String name;
    /**
     * 用户标签
     */
    private final String label;

    /**
     * 根据名称获取用户类型枚举
     * @param name
     * @return
     */
    public static UserTypeEums getUserTypeEums(String name){
        for (UserTypeEums value : values()) {
            if(value.getName().equals(name)){
                return value;
            }
        }
        return null;
    }
}
