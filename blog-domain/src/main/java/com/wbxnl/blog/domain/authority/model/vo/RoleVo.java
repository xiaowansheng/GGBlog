package com.wbxnl.blog.domain.authority.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 13:45
 */
@Data
public class RoleVo {

    private String name;

    private String label;

    private String description;

    private Integer disable;
}
