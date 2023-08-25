package com.wbxnl.blog.model.dto.extra;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "NameLabelDto",title = "NameLabelDto",description = "响应的名称和标签信息")
public class NameLabelDto {

    private String name;

    private String label;
}
