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
 * 相册
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_album")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("相册ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("相册创建人账号ID")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("相册名称")
    @TableField("name")
    private String name;

    // // @ApiModelProperty("相册描述")
    @TableField("description")
    private String description;

    // // @ApiModelProperty("相册封面")
    @TableField("cover")
    private String cover;

    // // @ApiModelProperty("相册状态（1公开2私密）")
    @TableField("status")
    private String status;

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
