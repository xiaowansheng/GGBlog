package com.wbxnl.blog.model.dto.extra;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "NameValueDto",title = "NameValueDto",description = "响应的键值对信息")
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
