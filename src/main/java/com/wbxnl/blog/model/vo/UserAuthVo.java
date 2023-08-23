package com.wbxnl.blog.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wbxnl.blog.model.dto.UserInfoDto;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.wbxnl.blog.validator.group.Add;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
 * 用户账号
 * 用于管理员创建账号的实体
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "UserAuthVo",title = "用户账号",description = "新增或修改用户账号数据时的提交的信息")
public class UserAuthVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "用户账号ID")
    @NotNull(message = "用户ID不能为空",groups = Update.class)
    @Null(message = "用户ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "登录用户名（一般是用户信息的邮箱）")
    private String username;

    @Schema(title = "用户密码")
    private String password;

    @Schema(title = "用户是否禁用")
    @Range(min = 0,max = 1,message = "是否禁用的值只能是0和1",groups = {Add.class, Update.class})
    @NotNull(message = "禁用属性不能为空",groups = {Add.class, Update.class})
    private Integer disable;
    /**
     * 用户信息
     */
    @Schema(title = "用户信息")
    @Valid
    private UserInfoVo userInfo;
    /**
     * 添加或编辑用户时，给用户赋予角色
     * 角色id
     */
    @Schema(title = "用户角色id集合",type = "List")
    @NotEmpty(message = "角色信息不能为空",groups = {Add.class, Update.class})
    private List<Integer> roleIds;

}
