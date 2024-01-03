package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.exception.BlogException;
import com.wbxnl.blog.model.dto.FileDto;
import com.wbxnl.blog.model.vo.FileUploadVo;
import com.wbxnl.blog.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author xiaowansheng
 * @Date 2024/1/3 10:46
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private String defaultPathPrefix="default";

    @Value("${upload.prefix.path}")
    private String pathPrefix;

    @Value("${upload.prefix.link}")
    private String linkPrefix;

    @Override
    public FileDto upload(FileUploadVo fileUploadVo, MultipartFile file) {
        if(file.isEmpty()){
            throw new BlogException(OperationStateCode.FILE_IS_EMPTY);
        }
        log.info("文件的大小是：{} byte", file.getSize());
        log.info("文件的类型是：{}", file.getContentType());

        String fileName = file.getOriginalFilename();
        log.info("文件的名称是：{}", fileName);

        // 处理文件上传
        // 文件名处理（避免文件重名覆盖），使用UUID
//        String exname = fileName.substring(fileName.lastIndexOf("."));
//        fileName = UUID.randomUUID().toString().replace("-", "") + exname;
        fileName = UUID.randomUUID().toString().replace("-", "")+"_"+fileName;
        // 检查或设置文件保存的文件夹
        if (!StringUtils.hasText(fileUploadVo.getFilePath())) {
            fileUploadVo.setFilePath(defaultPathPrefix);
        }
        String saveDirPath =pathPrefix+ fileUploadVo.getFilePath();
        File dirPathObj = new File(saveDirPath);
        // 总路径不存在，新建（这里路径必须要确保存在，以免下面转换的时候报FileNotFoundException）
        if (!dirPathObj.exists()) {
            dirPathObj.mkdirs();
        }
        String saveFilePath =saveDirPath + File.separator + fileName;
        // 将file转换到指定目录
        try {
            file.transferTo(new File(saveFilePath));
        } catch (IOException e) {
            throw new BlogException(OperationStateCode.FILE_SAVE_FAILURE);
        }
        // 返回文件保存的数据信息
        String targetFilePathTail=fileUploadVo.getFilePath() + "/" + fileName;
        FileDto fileDto = new FileDto()
                .setName(fileName)
                .setPath(fileUploadVo.getFilePath())
                .setType( file.getContentType())
                .setLink(linkPrefix + targetFilePathTail);
        return fileDto;
    }
}
