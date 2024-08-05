package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 16:58
 */
@Data
public class UserLoginReq {

    private String username;

    private String password;
}
