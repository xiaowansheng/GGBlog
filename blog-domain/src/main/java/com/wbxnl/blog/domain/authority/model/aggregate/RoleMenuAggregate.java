package com.wbxnl.blog.domain.authority.model.aggregate;

import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuSimpleEntity;
import lombok.Data;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 15:51
 */
@Data
public class RoleMenuAggregate {

    private Integer id;

    private String roleName;

    private String roleLabel;

    private List<String> menuNames;
}
