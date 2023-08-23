package com.wbxnl.blog.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 用户角色
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
//@ApiModel(value = "RoleVo对象", description = "用户角色")
@Schema(name = "RoleVo",title = "角色信息",description = "新增或修改角色时提交的信息")
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "角色ID")
    @NotNull(message = "角色ID不能为空",groups = Update.class)
    @Null(message = "角色ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "角色名称")
    @NotBlank(message = "角色名称不能为空",groups = {Add.class, Update.class})
    private String name;

    @Schema(title = "角色标签")
    @NotBlank(message = "角色标签不能为空",groups = {Add.class, Update.class})
    private String label;

    @Schema(title = "角色详情介绍")
    private String description;

    @Schema(title = "是否禁用")
    @NotNull(message = "禁用情况不能为空",groups = {Add.class, Update.class})
    @Range(min = 0,max = 1,message = "只能是0、1",groups = {Add.class,Update.class})
    private Integer disable;
}
