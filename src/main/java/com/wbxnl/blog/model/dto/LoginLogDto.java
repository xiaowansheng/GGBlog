package com.wbxnl.blog.model.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2023/8/7 22:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginLogDto implements Serializable {

    private static final long serialVersionUID = 1L;

    ////@ApiModelProperty("异常日志ID")
    private Integer id;

    ////@ApiModelProperty("操作用户ID")
    private Integer userAuthId;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    ////@ApiModelProperty("是否已删除")
    private Integer deleted;
}
