package com.wbxnl.blog.controller;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.AlbumDto;
import com.wbxnl.blog.model.entity.Album;
import com.wbxnl.blog.model.vo.AlbumVo;
import com.wbxnl.blog.model.vo.params.AlbumParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.utils.ConvertUtils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.AlbumService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 相册 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/album")
@Tag(name = "AlbumController",description = "我的相册模块")
public class AlbumController extends AbstractController<AlbumService, Album, AlbumDto, AlbumVo> {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/detail/{id}")
    @Operation(summary = "用户根据id查询相册详细数据")
    public Result<AlbumDto> getAlbumByUser(@PathVariable("id")Integer id){
        AlbumDto albumDto=albumService.getAlbumByUser(id);
        return new Result().ok(albumDto);
    }

    @GetMapping("/user/page")
    @Operation(summary = "用户分页查询相册信息")
    public Result<PageData<AlbumDto>> getPageByUser(@ParameterObject PageParams pageParams){
        PageData<AlbumDto> pageData = albumService.getPageByUser(pageParams,new AlbumParams());
        return new Result().ok(pageData);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询相册信息")
    public Result<PageData<AlbumDto>> getPage(@ParameterObject PageParams pageParams,@ParameterObject AlbumParams albumParams){
        PageData<AlbumDto> pageData = albumService.getPage(pageParams, albumParams);
        return new Result().ok(pageData);
    }
}
