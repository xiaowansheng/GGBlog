package com.wbxnl.blog.model.dto.extra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author xiaowansheng
 * @Date 2023/8/9 16:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NameLabelDto {

    private String name;

    private String label;
}
