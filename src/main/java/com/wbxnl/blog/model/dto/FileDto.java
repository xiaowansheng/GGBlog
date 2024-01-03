package com.wbxnl.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author xiaowansheng
 * @Date 2024/1/3 10:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class FileDto  implements Serializable {
    private static final long serialVersionUID = 1L;
//    private Integer id;
    private String name;
    private String path;
    private String link;
    private String type;
//    private long size;
//    private Date createTime;
//    private Date updateTime;
}
