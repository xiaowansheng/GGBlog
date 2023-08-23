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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统异常错误日志
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
@TableName("log_error")
//@ApiModel(value = "LogError对象", description = "系统异常错误日志")
public class LogError implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("异常日志ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("操作用户ID")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("操作用户名")
    @TableField("user_name")
    private String userName;

    // // @ApiModelProperty("系统版本")
    @TableField("version")
    private String version;

    // // @ApiModelProperty("请求的地址")
    @TableField("request_url")
    private String requestUrl;

    // // @ApiModelProperty("请求方法")
    @TableField("request_method")
    private String requestMethod;

    // // @ApiModelProperty("请求参数")
    @TableField("request_param")
    private String requestParam;

    // // @ApiModelProperty("请求模块")
    @TableField("module")
    private String module;

    // // @ApiModelProperty("调用方法")
    @TableField("calling_method")
    private String callingMethod;

    // // @ApiModelProperty("异常名称")
    @TableField("error_name")
    private String errorName;

    // // @ApiModelProperty("异常信息")
    @TableField("error_message")
    private String errorMessage;

    // // @ApiModelProperty("IP地址")
    @TableField("ip_address")
    private String ipAddress;

    // // @ApiModelProperty("ip所在地")
    @TableField("ip_source")
    private String ipSource;

    // // @ApiModelProperty("设备名称")
    @TableField("device")
    private String device;

    // // @ApiModelProperty("浏览器类型")
    @TableField("browser")
    private String browser;

    // // @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    // // @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // // @ApiModelProperty("是否已删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

}
