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
import com.wbxnl.blog.validator.custom.UserType;
import com.wbxnl.blog.validator.group.Add;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
 * <p>
 * 留言簿
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
@Schema(name = "GuestbookVo",title = "留言板",description = "提交和修改的留言信息")
public class GuestbookVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "留言ID")
    @NotNull(message = "留言ID不能为空",groups = Update.class)
    @Null(message = "留言ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "留言内容")
    @NotBlank(message = "留言内容不能为空",groups = Add.class)
    private String content;

    @Schema(title = "图片")
    private String images;

    @Schema(title = "坐标")
    private byte[] point;

    @Schema(title = "位置")
    private String location;

    @Schema(title = "操作用户类型(1登录用户2游客3匿名)")
    @UserType(groups = Add.class)
    private String type;

    @Schema(title = "游客别名")
    private String nickname;

    @Schema(title = "游客邮箱")
    @Email(message = "邮箱格式不正确",groups = Add.class)
    private String email;

    @Schema(title = "游客QQ号")
    private String qq;

    @Schema(title = "是否隐藏留言")
    @Range(min = 0,max = 1,message = "只能是0、1",groups = Update.class)
    @Null(message = "新增留言时禁止自定义隐藏情况",groups = Add.class)
    private Integer hidden;

    @Schema(title = "审核状态")
    @Range(min = -1,max = 1,message = "只能是-1、0、1",groups = Update.class)
    @Null(message = "新增留言时禁止自定义审核情况",groups = Add.class)
    private Integer review;
}
