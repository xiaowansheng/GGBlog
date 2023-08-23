package com.wbxnl.blog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wansheng
 * @createDate 2022/9/24 4:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "PasswordVo",title = "修改密码",description = "修改密码时提交的信息")
public class PasswordVo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Schema(title = "新密码")
    @NotBlank(message = "新的密码不能为空")
    private String newPassword;;

    @Schema(title = "旧密码")
    @NotBlank(message = "旧的密码不能为空")
    private String oldPassword;
}
