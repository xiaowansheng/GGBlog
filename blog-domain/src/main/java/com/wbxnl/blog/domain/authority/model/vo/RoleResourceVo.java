package com.wbxnl.blog.domain.authority.model.vo;

import lombok.Data;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 16:09
 */
@Data
public class RoleResourceVo {

    private String roleName;

    private List<String> resourceName;
}
