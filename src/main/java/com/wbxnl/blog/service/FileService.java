package com.wbxnl.blog.service;

import com.wbxnl.blog.model.dto.FileDto;
import com.wbxnl.blog.model.vo.FileUploadVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author xiaowansheng
 * @Date 2024/1/3 10:46
 */
public interface FileService {
    /**
     * 文件上传
     * @param fileUploadVo
     * @param file
     * @return
     */
    FileDto upload(FileUploadVo fileUploadVo, MultipartFile file);
}
