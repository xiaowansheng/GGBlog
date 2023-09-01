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
 * 相册
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
@Schema(name = "AlbumVo",title = "相册",description = "提交的相册信息")
public class AlbumVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "相册ID")
    @NotNull(message = "相册ID不能为空",groups = Update.class)
    @Null(message = "相册ID必须为空",groups = Add.class)
    private Integer id;

    private Integer userAuthId;

    @Schema(title = "相册名称")
    @NotBlank(message = "相册名称不能为空",groups = {Add.class, Update.class})
    private String name;

    @Schema(title = "相册描述")
    private String description;

    @Schema(title = "相册封面")
    @URL(message = "封面链接必须是合法的链接",groups = {Add.class, Update.class})
    private String cover;

    @Schema(title = "相册状态（1公开2私密...）")
    @AccessStatus(groups = {Add.class, Update.class})
    @NotBlank(message = "相册状态不能为空",groups = {Add.class, Update.class})
    private String status;

}
