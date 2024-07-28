package com.wbxnl.blog.domain.user.model.entity;

import lombok.Data;

/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 17:50
 */
@Data
public class EmailLoginEntity {
    /**
     * 默认为邮箱
     */
    private String username;

    private String password;
}
