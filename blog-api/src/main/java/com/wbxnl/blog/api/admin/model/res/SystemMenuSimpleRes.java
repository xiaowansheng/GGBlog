package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 16:54
 */
@Data
public class SystemMenuSimpleRes {

    private Integer id;

    private String menuKey;

    private String title;

    private String redirect;

    private String path;

    private Byte sort;

    private String parentKey;
}
