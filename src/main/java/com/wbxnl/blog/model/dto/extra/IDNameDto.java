package com.wbxnl.blog.model.dto.extra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xiaowansheng
 * @Date 2023/8/12 1:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IDNameDto {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
}
