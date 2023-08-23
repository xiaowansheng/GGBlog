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
 * 文章标签
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
@Schema(name = "TagVo",title = "文章标签信息",description = "新增或修改标签时提交的信息")
public class TagVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "文章标签ID")
    @NotNull(message = "标签ID不能为空",groups = Update.class)
    @Null(message = "标签ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "标签名称")
    @NotBlank(message = "标签名称不能为空",groups = {Add.class, Update.class})
    private String name;

    @Schema(title = "标签描述")
    private String description;

    @Schema(title = "是否隐藏该标签")
    @Range(min = 0,max = 1,message = "是否隐藏的值只能是0和1",groups = {Add.class, Update.class})
    @NotNull(message = "隐藏属性不能为空",groups = {Add.class, Update.class})
    private Integer hidden;
}
