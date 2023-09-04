package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.extra.IDNameDto;
import com.wbxnl.blog.model.dto.TagDto;
import com.wbxnl.blog.model.entity.Category;
import com.wbxnl.blog.model.entity.Tag;
import com.wbxnl.blog.model.vo.TagVo;
import com.wbxnl.blog.model.vo.extra.StatusVo;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.TagParams;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.TagService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章标签 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/tag")
@io.swagger.v3.oas.annotations.tags.Tag(name = "TagController", description = "文章标签模块")
public class TagController extends AbstractController<TagService, Tag, TagDto, TagVo> {
    @Autowired
    private TagService tagService;


    @PutMapping("/status")
    @Operation(summary = "改变标签状态信息")
    @Log(type = OperationType.UPDATE,desc = "改变标签状态信息")
    public Result updateStatus(@Validated({Update.class}) @RequestBody StatusVo statusVo){
        tagService.lambdaUpdate().eq(Tag::getId,statusVo.getId()).set(Tag::getHidden,statusVo.getStatus()).update();
        return new Result();
    }

    @GetMapping("/user/list")
    @Operation(summary = "用户获取所有的博客标签详细信息")
    public Result<List<TagDto>> getAllDetailByUser() {
        List<TagDto> tagDtos = tagService.getAllDetailByUser();
        return new Result().ok(tagDtos);
    }

    @GetMapping("/page")
    @Operation(summary = "获取所有的博客标签详细信息")
    public Result<PageData<TagDto>> getDetailPage(@ParameterObject PageParams pageParams, @ParameterObject TagParams tagParams) {
        PageData<TagDto> pageData = tagService.getPage(pageParams, tagParams);
        return new Result().ok(pageData);
    }


    @GetMapping("/simple/list")
    @Operation(summary = "获取所有的标签简略信息")
    public Result<List<IDNameDto>> getSimpleList() {
        List<Tag> tags = tagService.lambdaQuery().select(Tag::getId, Tag::getName).list();
        List<IDNameDto> nameDtos = tags.stream().map(tag -> new IDNameDto(tag.getId(), tag.getName())).collect(Collectors.toList());
        return new Result().ok(nameDtos);
    }

}
