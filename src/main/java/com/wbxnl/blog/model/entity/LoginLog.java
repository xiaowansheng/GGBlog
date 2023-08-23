package com.wbxnl.blog.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/7 22:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_login_log")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("用户账号")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("ip地址")
    @TableField("ip_address")
    private String ipAddress;

    // // @ApiModelProperty("ip来源")
    @TableField("ip_source")
    private String ipSource;

    // // @ApiModelProperty("设备名称")
    @TableField("device")
    private String device;

    // // @ApiModelProperty("浏览器类型")
    @TableField("browser")
    private String browser;

    // // @ApiModelProperty("坐标")
    @TableField("point")
    private byte[] point;

    // // @ApiModelProperty("坐标所在位置")
    @TableField("location")
    private String location;

    // // @ApiModelProperty("登录时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    // // @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // // @ApiModelProperty("是否删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

}
