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
@Deprecated
@Data
public class RoleMenuAggregate {

    private Integer id;

    private String roleKey;

    private String roleName;

    private List<String> menuKeys;
}
