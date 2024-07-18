package com.wbxnl.blog.domain.authority.model.eneity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 17:15
 */
@Data
public class RoleQueryEntity {

    private Integer id;

    private String name;

    private String label;

    private String description;

    private Integer disable;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
