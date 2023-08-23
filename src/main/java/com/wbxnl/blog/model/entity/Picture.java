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
 * 图片
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Accessors(chain = true)
@TableName("t_picture")
//@ApiModel(value = "Picture对象", description = "图片")
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("图片ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("图片上传人账号ID")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("相册ID")
    @TableField("album_id")
    private Integer albumId;

    // // @ApiModelProperty("图片名称")
    @TableField("name")
    private String name;

    // // @ApiModelProperty("图片描述")
    @TableField("description")
    private String description;

    // // @ApiModelProperty("图片地址")
    @TableField("url")
    private String url;

    // // @ApiModelProperty("图片来源（0未知1原创2二创3转载）")
    @TableField("source")
    private String source;

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
