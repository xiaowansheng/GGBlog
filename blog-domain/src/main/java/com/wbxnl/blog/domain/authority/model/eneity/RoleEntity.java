package com.wbxnl.blog.domain.authority.model.eneity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 13:46
 */
@Data
public class RoleEntity {

    private Integer id;

    private String roleKey;

    private String name;

    private String description;

    private Integer disable;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
