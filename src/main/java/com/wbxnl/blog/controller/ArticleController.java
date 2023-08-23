package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.ArticleDto;
import com.wbxnl.blog.model.dto.extra.StatisticsOfNumberDto;
import com.wbxnl.blog.model.entity.Article;
import com.wbxnl.blog.model.vo.ArticleVo;
import com.wbxnl.blog.model.vo.params.ArticleParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.*;
import com.wbxnl.blog.utils.ConvertUtils;
import com.wbxnl.blog.utils.SecurityUtils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.validator.group.Add;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
//import io.swagger.documentation.annotations.ApiIgnore;

import java.util.HashMap;

/**
 * <p>
 * 博客文章 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/article")
@Tag(name = "ArticleController",description = "博客文章模块")
public class ArticleController extends AbstractController<ArticleService, Article, ArticleDto, ArticleVo> {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TalkService talkService;


    @GetMapping("/detail/{id}")
    @Operation(summary = "游客查询文章详细数据")
    public Result getDetailByUser(@PathVariable Integer id){
        ArticleDto articleDtoByUser = articleService.getArticleDtoByUser(id);
        return new Result().ok(articleDtoByUser);
    }

    @GetMapping("/user/archive")
    @Operation(summary = "游客查询文章归档信息")
    public Result getArchiveByUser(@ParameterObject PageParams pageParams){
        PageData<ArticleDto> archive = articleService.getArchiveByUser(pageParams);
        return new Result().ok(archive);
    }

    @GetMapping("/user/statistics/number")
    @Operation(summary = "用户查询文章、分类、标签等统计信息")
    public Result getStatisticsOfNumberByUser(){
        StatisticsOfNumberDto statistics=articleService.getStatisticsOfCountByUser();
        return new Result().ok(statistics);
    }

    @GetMapping("/user/page")
    @Operation(summary = "用户分页查询文章列表",description = "主页文章列表展示,分类和标签详情展示")
    public Result getPageByUser(@ParameterObject PageParams pageParams, @ParameterObject ArticleParams articleParams){
        PageData<ArticleDto> pageData=articleService.getPageByUser(pageParams,articleParams);
        return new Result().ok(pageData);
    }


    @GetMapping("/statistics/number")
    @Operation(summary = "查询文章、分类、标签数量统计")
    public Result getStatisticsOfNumber(){
        StatisticsOfNumberDto statistics = articleService.getStatisticsOfCount();
        return new Result().ok(statistics);
    }

    @GetMapping("/archive")
    @Operation(summary = "查询文章归档信息",description = "展示文章的创造路线")
    public Result getArchive(@ParameterObject PageParams pageParams){
        PageData<ArticleDto> pageData=articleService.getArchive(pageParams);
        return new Result().ok(pageData);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询文章列表")
    public Result getPage(@ParameterObject PageParams pageParams, @ParameterObject ArticleParams articleParams){
        PageData<ArticleDto> pageData=articleService.getPage(pageParams,articleParams);
        return new Result().ok(pageData);
    }

    @PutMapping("/simple")
    @Log(type = OperationType.UPDATE,desc ="修改文章信息")
    @Operation(summary = "简单修改文章信息，不包括文章内容")
    public Result updateSimple(@Validated(Update.class) @RequestBody  ArticleVo articleVo){
        Article article = ConvertUtils.sourceToTarget(articleVo, Article.class);
        articleService.updateById(article);
        return new Result();
    }

    @PostMapping("/draft")
    @Log(type = OperationType.ADD,desc ="保存草稿")
    @Operation(summary = "快速保存文章草稿",description = "保存草稿信息并返回文章ID")
    public Result saveDraft(@Validated(Add.class) @RequestBody ArticleVo articleVo){
        ArticleDto articleDto=articleService.saveDraft(articleVo);
        // 只返回ID
        return new Result().ok(ArticleDto.builder().id(articleDto.getId()).build());
    }
}
