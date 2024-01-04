package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.model.dto.FileDto;
import com.wbxnl.blog.model.vo.FileUploadVo;
import com.wbxnl.blog.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件自定义上传处理
 * @Author xiaowansheng
 * @Date 2024/1/3 10:42
 */
@RestController
@RequestMapping("/file")
@Tag(name = "FileController", description = "文件操作模块")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    @Operation(summary = "上传文件")
    @Log(type = OperationType.ADD,desc = "上传文件")
    public Result<FileDto> upload(FileUploadVo fileUploadVo,@RequestParam(value = "file")MultipartFile file) {
        FileDto fileDto = fileService.upload(fileUploadVo,file);
        return new Result<FileDto>().ok(fileDto);
    }
}
