package com.wbxnl.blog.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 相册
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "AlbumDto",title = "AlbumDto",description = "响应的相册信息")
public class AlbumDto implements Serializable {


    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userAuthId;

    private String name;

    private String description;

    private String cover;

    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 是否已删除
     */
    private Integer deleted;

    /**
     * （额外）访问量
     */
    private Long pageView;
    /**
     * （额外）评论数量
     */
    private Long commentCount;
}
