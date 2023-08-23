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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

/**
 * <p>
 * 用户信息表
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
@Schema(name = "UserInfoVo",title = "用户信息",description = "新增或修改用户个人信息时的提交数据")
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "信息ID")
    @NotNull(message = "信息ID不能为空",groups = Update.class)
    @Null(message = "信息ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "用户邮箱")
    @Email(message = "邮箱格式不正确",groups = {Add.class, Update.class})
    private String email;

    @Schema(title = "用户qq")
    private String qq;

    @Schema(title = "用户名")
    private String nickname;

    @Schema(title = "个人头像")
    @URL(message = "头像链接不合法",groups = {Add.class, Update.class})
    private String avatar;

    @Schema(title = "个人签名")
    private String signature;

    @Schema(title = "个人网站")
    private String website;

    @Schema(title = "个人简介")
    private String introduction;
}
