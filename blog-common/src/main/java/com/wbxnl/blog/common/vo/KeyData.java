package com.wbxnl.blog.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 10:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyData {

    private Integer id;

    private String key;
}
