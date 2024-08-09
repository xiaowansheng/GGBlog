package com.wbxnl.blog.domain.authority.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 16:09
 */
@Data
@AllArgsConstructor
public class RoleMenuVo {

    private String roleKey;

    private List<String> menuKeys;
}
