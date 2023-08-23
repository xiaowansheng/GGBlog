package com.wbxnl.blog.model.vo;

import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wbxnl.blog.validator.group.Add;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * 友情链接
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
@Schema(name = "FriendVo",title = "友情链接",description = "提交和修改的友情链接")
public class FriendVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "友链编号")
    @NotNull(message = "友链ID不能为空",groups = Update.class)
    @Null(message = "友链ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "网站名称")
    @NotBlank(message = "网站名称不能为空",groups = Add.class)
    private String name;

    @Schema(title = "网站图标链接")
    @NotBlank(message = "网站图标不能为空",groups = Add.class)
    private String icon;

    @Schema(title = "网站地址")
    @NotBlank(message = "网站地址不能为空",groups = Add.class)
    private String url;

    @Schema(title = "网站作者")
    @NotBlank(message = "网站作者不能为空",groups = Add.class)
    private String author;

    @Schema(title = "网站介绍")
    private String introduction;

    @Schema(title = "审核状态（0待审核1通过-1未通过）")
    @Range(min = -1,max = 1,message = "只能是-1、0、1",groups = Update.class)
    @Null(message = "新增友链时禁止自定义审核情况",groups = Add.class)
    private Integer review;

    @Schema(title = "是否隐藏")
    @Range(min = -1,max = 1,message = "只能是0、1",groups = Update.class)
    @Null(message = "新增友链时禁止自定义隐藏情况",groups = Add.class)
    private Integer hidden;
}
