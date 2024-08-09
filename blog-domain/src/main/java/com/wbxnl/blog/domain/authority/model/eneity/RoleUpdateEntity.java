package com.wbxnl.blog.domain.authority.model.eneity;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 13:45
 */
@Data
public class RoleUpdateEntity {

    private Integer id;

    private String name;

    private String description;

    private Integer disable;
}
