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
import org.hibernate.validator.constraints.Range;

/**
 * <p>
 * 说说
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
@Schema(name = "TalkVo",title = "说说日志",description = "新增或修改说说时提交的信息")
public class TalkVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "说说ID")
    @NotNull(message = "说说ID不能为空",groups = Update.class)
    @Null(message = "说说ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(title = "说说内容")
//    @NotBlank(message = "说说内容不能为空",groups = {Add.class, Update.class})
    private String content;

    @Schema(title = "图片")
    private String images;

    @Schema(title = "状态（1显示2私密）")
    @AccessStatus
    private String status;

    @Schema(title = "置顶（1置顶0不置顶）")
    @Range(min = 0,max = 1,message = "是否置顶的值只能是0和1",groups = {Add.class, Update.class})
    @NotNull(message = "置顶属性不能为空",groups = {Add.class, Update.class})
    private Integer top;

    @Schema(title = "坐标")
    private byte[] point;

    @Schema(title = "位置")
    private String location;
}
