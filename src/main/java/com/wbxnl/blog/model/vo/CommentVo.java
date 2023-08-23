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
import com.wbxnl.blog.validator.custom.TopicType;
import com.wbxnl.blog.validator.custom.UserType;
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
 * 博客评论
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
@Schema(name = "CommentVo",title = "评论",description = "提交和修改的评论内容")
public class CommentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "评论编号")
    @NotNull(message = "评论ID不能为空",groups = Update.class)
    @Null(message = "评论ID必须为空",groups = Add.class)
    private Integer id;

    @Schema(description = "评论类型（1文章2说说3写作4友联）")
    @TopicType
    @NotBlank(message = "话题类型不能为空",groups = {Add.class})
    private String topicType;

    @Schema(description = "评论的主题ID（文章ID说说ID...）")
    private Integer topicId;

    @Schema(description = "评论内容")
    @NotBlank(message = "评论内容不能为空",groups = Add.class)
    private String content;

    @Schema(description = "图片")
    private String images;

    @Schema(description = "坐标")
    private byte[] point;

    @Schema(description = "位置")
    private String location;

    @Schema(description = "根评论ID")
    private Integer rootId;

    @Schema(description = "回复的评论ID")
    private Integer parentId;

    @Schema(description = "评论类型(1登录评论2游客评论3匿名评论)")
    @UserType(groups = Add.class)
    private String type;

    @Schema(description = "游客别名")
    private String nickname;

    @Schema(description = "游客邮箱")
    private String email;

    @Schema(description = "游客QQ号")
    private String qq;

    @Schema(description = "是否隐藏评论")
    @Range(min = 0,max = 1,message = "只能为0和1",groups = Update.class)
    @Null(message = "新增评论时禁止自定义隐藏",groups = Add.class)
    private Integer hidden;

    @Schema(description = "是否审核评论再显示")
    @Range(min = -1,max = 1,message = "审核情况只能是-1、0、1",groups = Update.class)
    @Null(message = "新增评论时禁止自定义审核情况",groups = Add.class)
    private Integer review;

    /**
     * （额外）用作通知用户
     */
    @Schema(description = "评论所在页面链接")
    @NotBlank(message = "评论内容时所在的页面链接不能为空",groups = Add.class)
    private String url;
}
