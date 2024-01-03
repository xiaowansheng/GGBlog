package com.wbxnl.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Author xiaowansheng
 * @Date 2024/1/3 10:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String filePath;

//    private MultipartFile file;
}
