package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 10:15
 */
@Data
public class SystemMenuInfoRes {

    private Integer id;

    private String menuKey;

    private String title;

    private String icon;

    private String redirect;

    private String path;

    private String component;

    private Integer hidden;

    private Byte sort;

    private Integer parentId;

    private String perms;

    private List<SystemMenuInfoRes> children;
}
