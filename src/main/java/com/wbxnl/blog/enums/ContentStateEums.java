package com.wbxnl.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wansheng
 * @Date 2022/9/11 21:53
 */
@Getter
@AllArgsConstructor
public enum ContentStateEums {
    PUBLIC("public", "公开"),
    PRIVATE("private", "私密"),
    Login("login", "登录可见"),
    COMMENT("comment", "评论可见");
    /**
     * 文章状态码
     */
    private final String name;

    /**
     * 文章状态名称
     */
    private final String label;

    /**
     * 根据访问状态名，获取对应的枚举信息
     * @param name
     * @return
     */
    public static ContentStateEums getContentStatusEums(String name){
        for (ContentStateEums value : values()) {
            if(value.getName().equals(name)){
                return value;
            }
        }
        return null;
    }
}
