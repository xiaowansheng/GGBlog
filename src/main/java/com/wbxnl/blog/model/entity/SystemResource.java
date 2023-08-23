package com.wbxnl.blog.model.entity;

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
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源菜单
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("system_resource")
//@ApiModel(value = "SystemResource对象", description = "资源菜单")
public class SystemResource implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("资源菜单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("资源名称")
    @TableField("name")
    private String name;

    // // @ApiModelProperty("请求方法")
    @TableField("request_method")
    private String requestMethod;

    // // @ApiModelProperty("资源访问路径")
    @TableField("path")
    private String path;

    // // @ApiModelProperty("是否开放资源（1开放0不开放）")
    @TableField("open")
    private Integer open;

    // // @ApiModelProperty("父类ID")
    @TableField("parent_id")
    private Integer parentId;

    // // @ApiModelProperty("权限标识")
    @TableField("perms")
    private String perms;

    // // @ApiModelProperty("资源描述")
    @TableField("description")
    private String description;

    ////@ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    ////@ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    ////@ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
