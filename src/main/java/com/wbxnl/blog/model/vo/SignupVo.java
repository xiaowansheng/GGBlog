package com.wbxnl.blog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author xiaowansheng
 * @Date 2023/8/9 16:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "SignupVo", title = "注册信息", description = "用户注册时所需的相关信息")
public class SignupVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱不合法")
    private String email;

    @Schema(title = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;

    @Schema(title = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @Schema(title = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
