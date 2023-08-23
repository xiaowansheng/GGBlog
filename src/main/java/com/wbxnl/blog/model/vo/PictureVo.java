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
import com.wbxnl.blog.validator.custom.AccessStatus;
import com.wbxnl.blog.validator.custom.PictureType;
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
import org.hibernate.validator.constraints.URL;

/**
 * <p>
 * 图片
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
@Schema(name = "PictureVo", title = "图片信息", description = "添加和修改图片时提交的信息")
public class PictureVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "图片ID")
    @NotNull(message = "照片ID不能为空", groups = Update.class)
    @Null(message = "照片ID必须为空", groups = Add.class)
    private Integer id;

    @Schema(title = "相册ID")
    @NotNull(message = "相册编号不能为空", groups = Add.class)
    private Integer albumId;

    @Schema(title = "图片名称")
    private String name;

    @Schema(title = "图片描述")
    private String description;

    @Schema(title = "图片地址")
    @URL(message = "图片链接无效")
    @NotBlank(message = "图片链接不能为空")
    private String url;

    @Schema(title = "图片来源（0未知1原创2二创3转载）")
    @PictureType(groups = {Add.class, Update.class})
    private String source;

    @Schema(title = "图片状态")
    @AccessStatus(groups = {Add.class, Update.class})
    private String status;
}
