package com.wbxnl.blog.model.vo;

import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
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
 * 文章类别
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
@Schema(name = "CategoryVo",title = "文章分类",description = "提交的添加和修改文章分类数据")
public class CategoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "分类ID")
    @NotNull(message = "分类ID不能为空",groups = Update.class)
    @Null(message = "分类ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "类别名")
    @NotBlank(message = "分类名不能为空",groups = {Add.class, Update.class})
    private String name;

    @Schema(title = "分类描述")
    private String description;

    @Schema(title = "是否隐藏该分类")
    @Range(min = 0,max = 1,message = "是否隐藏的值只能是0和1",groups = {Add.class, Update.class})
    @NotNull(message = "隐藏属性不能为空",groups = {Add.class, Update.class})
    private Integer hidden;
}
