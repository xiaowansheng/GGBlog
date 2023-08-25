package com.wbxnl.blog.model.dto.extra;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "IDNameDto",title = "IDNameDto",description = "响应的ID和名称信息")
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
