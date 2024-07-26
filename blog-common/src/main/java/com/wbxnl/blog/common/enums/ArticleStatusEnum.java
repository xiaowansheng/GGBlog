package com.wbxnl.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/26 18:17
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {
    /**
     * 1公开
     */
    OPEN("open"),
    /**
     * 2私密
     */
    PRIVATE("private"),
    /**
     * 3密码可见
     */
    PASSWORD_VISIBLE("password"),
    /**
     * 4登录可见
     */
    LOGIN_VISIBLE("login"),
    /**
     * 5评论可见
     */
    COMMENT_VISIBLE("comment");

    private final String status;
}
