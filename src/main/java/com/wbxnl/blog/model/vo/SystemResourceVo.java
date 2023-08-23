package com.wbxnl.blog.model.vo;

import java.io.Serializable;
import java.util.Date;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wbxnl.blog.validator.custom.RequestMethod;
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
 * 资源菜单
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
//@ApiModel(value = "SystemResourceVo对象", description = "资源菜单")
@Schema(name = "SystemResourceVo",title = "系统资源信息",description = "新增或修改资源信息时提交的数据")
public class SystemResourceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "资源ID")
    @NotNull(message = "资源ID不能为空",groups = Update.class)
    @Null(message = "资源ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "资源名称")
    @NotBlank(message = "资源名称不能为空",groups = {Add.class, Update.class})
    private String name;

    @Schema(title = "请求方法")
    @NotBlank(message = "请求方法不能为空",groups = {Add.class, Update.class})
    @RequestMethod(groups = {Add.class, Update.class})
    private String requestMethod;

    @Schema(title = "资源访问路径")
    @NotBlank(message = "资源访问路径不能为空",groups = {Add.class, Update.class})
    private String path;

    @Schema(title = "是否开放资源（1开放0不开放）")
    @NotNull(message = "状态设置不能为空",groups = {Add.class, Update.class})
    @Range(min = 0,max = 1,message = "只能是0、1",groups = {Add.class,Update.class})
    private Integer open;

    @Schema(title = "父类ID")
    private Integer parentId;

    @Schema(title = "权限标识")
    private String perms;

    @Schema(title = "资源描述")
    private String description;
}
