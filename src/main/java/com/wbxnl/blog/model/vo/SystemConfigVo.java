package com.wbxnl.blog.model.vo;

import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.wbxnl.blog.validator.group.Add;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 网站配置
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "SystemConfigVo",title = "系统配置信息",description = "新增和修改配置时，提交的信息")
public class SystemConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "配置ID")
    @NotNull(message = "配置ID不能为空",groups = Update.class)
    @Null(message = "配置ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "配置参数名")
    @NotBlank(message = "配置名称不能为空",groups = {Add.class, Update.class})
    private String name;

    @Schema(title = "参数名称")
    @NotBlank(message = "配置标签不能为空",groups = {Add.class, Update.class})
    private String label;

    @Schema(title = "配置参数值")
    @NotBlank(message = "配置参数值不能为空",groups = {Add.class, Update.class})
    private String value;

    @Schema(title = "配置描述信息")
    private String description;
}
