package com.wbxnl.blog.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志
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
@TableName("log_operation")
//@ApiModel(value = "LogOperation对象", description = "操作日志")
public class LogOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    // // @ApiModelProperty("操作日志ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // // @ApiModelProperty("操作用户ID")
    @TableField("user_auth_id")
    private Integer userAuthId;

    // // @ApiModelProperty("操作用户昵称")
    @TableField("user_name")
    private String userName;

    // // @ApiModelProperty("项目版本")
    @TableField("version")
    private String version;

    // // @ApiModelProperty("请求地址")
    @TableField("request_url")
    private String requestUrl;

    // // @ApiModelProperty("操作模块")
    @TableField("module")
    private String module;

    // // @ApiModelProperty("调用方法")
    @TableField("calling_method")
    private String callingMethod;

    // // @ApiModelProperty("操作类型（新增、修改...）")
    @TableField("type")
    private String type;

    // // @ApiModelProperty("操作描述信息")
    @TableField("description")
    private String description;

    // // @ApiModelProperty("请求方法")
    @TableField("request_method")
    private String requestMethod;

    // // @ApiModelProperty("请求参数")
    @TableField("request_param")
    private String requestParam;

    // // @ApiModelProperty("响应参数")
    @TableField("response_data")
    private String responseData;

    // // @ApiModelProperty("操作执行耗时毫秒数")
    @TableField("elapsed_time")
    private Long elapsedTime;

    // // @ApiModelProperty("请求IP")
    @TableField("ip_address")
    private String ipAddress;

    // // @ApiModelProperty("ip归属地")
    @TableField("ip_source")
    private String ipSource;

    // // @ApiModelProperty("设备名称")
    @TableField("device")
    private String device;

    // // @ApiModelProperty("浏览器名称")
    @TableField("browser")
    private String browser;

    ////@ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    ////@ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    ////@ApiModelProperty("是否被删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
