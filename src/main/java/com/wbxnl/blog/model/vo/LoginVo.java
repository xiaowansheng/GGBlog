package com.wbxnl.blog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 登录时，接收的对象实体
 * @Author xiaowansheng
 * @Date 2023/8/8 17:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "LoginVo",title = "登录",description = "登录时提交的信息")
public class LoginVo implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    @Schema(title = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 用户密码
     */
    @Schema(title = "用户密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
