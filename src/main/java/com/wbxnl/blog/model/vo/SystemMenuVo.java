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
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 菜单目录
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
@Schema(name = "SystemMenuVo",title = "后台菜单信息",description = "新增或修改后台菜单时提交的信息")
public class SystemMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "菜单ID")
    @NotNull(message = "菜单ID不能为空",groups = Update.class)
    @Null(message = "菜单ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "菜单名称")
    @NotBlank(message = "菜单名称不能为空",groups = {Add.class, Update.class})
    private String name;

    @Schema(title = "菜单标题")
    @NotBlank(message = "菜单标签不能为空",groups = {Add.class, Update.class})
    private String title;

    @Schema(title = "图标")
    @NotBlank(message = "菜单图标不能为空",groups = {Add.class, Update.class})
    private String icon;

    @Schema(title = "路由重定向")
    private String redirect;

    @Schema(title = "路由地址")
    @NotBlank(message = "路由地址不能为空",groups = {Add.class, Update.class})
    private String path;

    @Schema(title = "组件路径")
//    @NotBlank(message = "组件地址不能为空",groups = {Add.class, Update.class})
    private String component;

    @Schema(title = "隐藏菜单（0展示1隐藏）")
    @NotNull(message = "隐藏设置不能为空",groups = {Add.class, Update.class})
    @Range(min = 0,max = 1,message = "只能是0、1",groups = {Add.class,Update.class})
    private Integer hidden;

    @Schema(title = "排序")
    @NotNull(message = "排序字段不能为空",groups = {Add.class, Update.class})
    private Byte sort;

    @Schema(title = "父类ID")
    private Integer parentId;

    @Schema(title = "权限标识")
    private String perms;

    @Schema(title = "描述信息")
    private String description;
}
