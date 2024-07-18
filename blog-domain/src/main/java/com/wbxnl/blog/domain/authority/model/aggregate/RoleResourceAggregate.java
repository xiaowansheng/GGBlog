package com.wbxnl.blog.domain.authority.model.aggregate;

import lombok.Data;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 15:51
 */
@Data
public class RoleResourceAggregate {

    private Integer id;

    private String roleName;

    private String roleLabel;

    private List<String> resourceNames;

}
