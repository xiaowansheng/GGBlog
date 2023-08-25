package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Api;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.PictureDto;
import com.wbxnl.blog.model.entity.Picture;
import com.wbxnl.blog.model.vo.PictureVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.PictureParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.PictureService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 图片 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/picture")
@Tag(name ="PictureController" ,description = "相册照片模块")
public class PictureController extends AbstractController<PictureService, Picture, PictureDto, PictureVo> {
    @Autowired
    private PictureService pictureService;

    @GetMapping("/user/page")
    @Operation(summary = "用户分页查询照片")
    public Result<PageData<PictureDto>> getPageByUser(@ParameterObject PageParams pageParams){
        PageData<PictureDto> pageData =pictureService.getPageByUser(pageParams,new PictureParams());
        return new Result().ok(pageData);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询照片数据")
    public Result<PageData<PictureDto>> getPage(@ParameterObject PageParams pageParams, @ParameterObject PictureParams pictureParams){
        PageData<PictureDto> pageData =pictureService.getPage(pageParams,pictureParams);
        return new Result().ok(pageData);
    }
}
