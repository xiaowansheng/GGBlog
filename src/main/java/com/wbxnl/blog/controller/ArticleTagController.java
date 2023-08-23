package com.wbxnl.blog.controller;

import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.ArticleTagDto;
import com.wbxnl.blog.model.entity.ArticleTag;
import com.wbxnl.blog.model.vo.ArticleTagVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.ArticleTagService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章对应标签 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/article/tag")
@Tag(name = "ArticleTagController", description = "文章标签模块")
public class ArticleTagController extends AbstractController<ArticleTagService, ArticleTag, ArticleTagDto, ArticleTagVo> {

}
