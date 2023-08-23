package com.wbxnl.blog.model.dto.extra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xiaowansheng
 * @Date 2023/8/16 21:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameValueDto {
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     *  可能是Array、Object、Value,或者其他
     */
    private Object value;
}
