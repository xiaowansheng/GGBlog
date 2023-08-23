package com.wbxnl.blog.model.vo.params;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 网站配置
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SystemConfigParams implements Serializable {


    @Parameter(description = "配置ID")
    private Integer id;

    @Parameter(description = "配置名称")
    private String name;

    @Parameter(description = "配置标签")
    private String label;

    @Parameter(description = "配置参数值")
    private String value;

    @Parameter(description = "配置描述")
    private String description;
}
