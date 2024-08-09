package com.wbxnl.blog.domain.authority.model.vo;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 9:40
 */
@Data
public class SystemMenuVo {

    private String menuKey;

    private String title;

    private String icon;

    private String redirect;

    private String path;

    private String component;

    private Integer hidden;

    private Byte sort;

    private String parentKey;

    private String perms;

    private String description;
}
