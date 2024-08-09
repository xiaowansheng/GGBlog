package com.wbxnl.blog.domain.authority.model.eneity;


import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 16:47
 */
@Data
public class SystemMenuSimpleEntity {

    private Integer id;

    private String menuKey;

//    private String name;

    private String title;

    private Byte sort;

    private String rootKey;

    private String parentKey;

    private String description;
}
