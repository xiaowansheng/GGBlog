package com.wbxnl.blog.domain.authority.model.eneity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 17:34
 */
@Data
public class UserMenuEntity {

    private String name;

    private String title;

    private String icon;

    private String redirect;

    private String path;

    private String component;

    private Byte sort;

    private Integer parentId;
}
