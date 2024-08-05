package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 0:48
 */
@Data
public class RoleDataReq {

    private String roleKey;

    private String name;

    private String description;

    private Integer disable;
}
