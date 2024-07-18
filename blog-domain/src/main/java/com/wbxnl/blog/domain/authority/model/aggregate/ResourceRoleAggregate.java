package com.wbxnl.blog.domain.authority.model.aggregate;

import lombok.Data;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 17:52
 */
@Data
public class ResourceRoleAggregate {

    private Integer id;

    private String resourceName;

    private String resourceLabel;

    private List<String> roleNames;
}
