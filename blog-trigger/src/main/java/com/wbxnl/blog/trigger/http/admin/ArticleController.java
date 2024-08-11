package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.*;
import com.wbxnl.blog.api.admin.model.res.*;
import com.wbxnl.blog.api.admin.service.IArticleService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.CategoryAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.TagAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.ArticleDraftVo;
import com.wbxnl.blog.domain.article.model.vo.ArticleVo;
import com.wbxnl.blog.domain.article.model.vo.CategoryVo;
import com.wbxnl.blog.domain.article.model.vo.TagVo;
import com.wbxnl.blog.domain.article.service.ICategoryService;
import com.wbxnl.blog.domain.article.service.ITagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/5 10:56
 */
@Slf4j
@RestController
@RequestMapping("/admin/article")
@RequiredArgsConstructor
public class ArticleController implements IArticleService {

    private final com.wbxnl.blog.domain.article.service.IArticleService articleService;

    private final ICategoryService categoryService;

    private final ITagService tagService;

    @PostMapping("/publish")
    @Override
    public KeyData publishArticle(ArticleDataReq articleDataReq) {
        ArticleVo articleVo = ObjectConvertUtils.convert(articleDataReq, ArticleVo.class);
        ArticleEntity articleEntity = articleService.addArticle(articleVo);
        return KeyData.builder()
                .id(articleEntity.getId())
                .key(articleEntity.getArticleKey())
                .build();
    }

    @PostMapping("/draft")
    @Override
    public KeyData addOrUpdateArticleDraft(ArticleDataReq articleDataReq) {
        ArticleDraftVo articleDraftVo = ObjectConvertUtils.convert(articleDataReq, ArticleDraftVo.class);
        ArticleEntity articleEntity = articleService.saveArticleDraft(articleDraftVo);
        // TODO 还要返回分类的key和标签的key
        return KeyData.builder()
                .id(articleEntity.getId())
                .key(articleEntity.getArticleKey())
                .build();
    }

    @PutMapping("/status")
    @Override
    public void updateArticleTop(Integer id, Integer top) {
        articleService.updateArticleTop(id, top);
    }

    @PutMapping("/basic")
    @Override
    public void updateArticleBasicInfo(ArticleBasicDataReq articleBasicDataReq) {
        ArticleBasicUpdateEntity basicUpdateEntity = ObjectConvertUtils.convert(articleBasicDataReq, ArticleBasicUpdateEntity.class);
        articleService.updateArticleBasicInfo(basicUpdateEntity);
    }

    @DeleteMapping("")
    @Override
    public void deleteArticle(Integer id) {
        articleService.deleteArticle(id);
    }

    @DeleteMapping("/batch")
    @Override
    public void deleteArticle(Integer[] ids) {
        articleService.deleteArticle(ids);
    }

    @GetMapping("/page")
    @Override
    public PageData<ArticleInfoRes> getPageOfArticles(PageParams pageParams, ArticleQueryReq articleQueryReq) {
        ArticleQueryEntity articleQueryEntity = ObjectConvertUtils.convert(articleQueryReq, ArticleQueryEntity.class);
        PageData<ArticleAggregate> pageOfArticleDetails = articleService.getPageOfArticleDetails(pageParams, articleQueryEntity);
        List<ArticleInfoRes> articleInfoResList = ObjectConvertUtils.convertList(pageOfArticleDetails.getData(), ArticleInfoRes.class);
        return PageData.<ArticleInfoRes>builder()
                .number(pageOfArticleDetails.getNumber())
                .size(pageOfArticleDetails.getSize())
                .total(pageOfArticleDetails.getTotal())
                .data(articleInfoResList)
                .build();
    }

    @GetMapping("/archive")
    @Override
    public PageData<ArticleInfoRes> getArchive(PageParams pageParams, ArticleQueryReq articleQueryReq) {
        ArticleQueryEntity articleQueryEntity = ObjectConvertUtils.convert(articleQueryReq, ArticleQueryEntity.class);
        PageData<ArticleAggregate> pageOfArticleDetails = articleService.getPageOfArticleDetails(pageParams, articleQueryEntity);
        List<ArticleInfoRes> articleInfoResList = ObjectConvertUtils.convertList(pageOfArticleDetails.getData(), ArticleInfoRes.class);
        return PageData.<ArticleInfoRes>builder()
                .number(pageOfArticleDetails.getNumber())
                .size(pageOfArticleDetails.getSize())
                .total(pageOfArticleDetails.getTotal())
                .data(articleInfoResList)
                .build();
    }

    @GetMapping("/detail")
    @Override
    public ArticleDetailRes getArticleDetail(Integer id) {
        ArticleAggregate articleDetail = articleService.getArticleDetail(id);
        return ObjectConvertUtils.convert(articleDetail, ArticleDetailRes.class);
    }

    @GetMapping("/statistics")
    @Override
    public List<KeyAndValueRes> getArticleStatisticsOfLastMonth() {
        // TODO
        return null;
    }

    @PostMapping("/category")
    @Override
    public KeyData addCategory(CategoryDataReq categoryDataReq) {
        CategoryVo categoryVo = ObjectConvertUtils.convert(categoryDataReq, CategoryVo.class);
        CategoryEntity categoryEntity = categoryService.addCategory(categoryVo);
        Assert.notNull(categoryEntity, "添加分类失败");
        return KeyData.builder()
                .id(categoryEntity.getId())
                .key(categoryEntity.getCategoryKey())
                .build();
    }

    @PutMapping("/category")
    @Override
    public void updateCategory(CategoryDataReq categoryDataReq) {
        CategoryUpdateEntity categoryUpdateEntity = ObjectConvertUtils.convert(categoryDataReq, CategoryUpdateEntity.class);
        categoryService.updateCategory(categoryUpdateEntity);
    }

    @PostMapping("/category/status")
    @Override
    public void updateCategoryStatus(Integer id, String status) {
        categoryService.updateCategoryStatus(id, status);
    }

    @DeleteMapping("/category")
    @Override
    public void deleteCategory(Integer id) {
        categoryService.deleteCategory(id);
    }

    @DeleteMapping("/category/batch")
    @Override
    public void deleteCategory(Integer[] ids) {
        categoryService.deleteCategory(ids);
    }

    @GetMapping("/category/page")
    @Override
    public PageData<CategoryDetailRes> getPageOfCategory(PageParams pageParams, CategoryQueryReq categoryQueryReq) {
        CategoryQueryEntity categoryQueryEntity = ObjectConvertUtils.convert(categoryQueryReq, CategoryQueryEntity.class);
        PageData<CategoryAggregate> pageOfCategoryDetails = categoryService.getPageOfCategories(pageParams, categoryQueryEntity);
        List<CategoryDetailRes> categoryDetailResList = ObjectConvertUtils.convertList(pageOfCategoryDetails.getData(), CategoryDetailRes.class);
        return PageData.<CategoryDetailRes>builder()
                .number(pageOfCategoryDetails.getNumber())
                .size(pageOfCategoryDetails.getSize())
                .total(pageOfCategoryDetails.getTotal())
                .data(categoryDetailResList)
                .build();
    }

    @PostMapping("/tag")
    @Override
    public KeyData addTag(TagDataReq tagDataReq) {
        TagVo tagVo = ObjectConvertUtils.convert(tagDataReq, TagVo.class);
        TagEntity tagEntity = tagService.addTag(tagVo);
        Assert.notNull(tagEntity, "添加标签失败");
        return KeyData.builder()
                .id(tagEntity.getId())
                .key(tagEntity.getTagKey())
                .build();
    }

    @PutMapping("/tag")
    @Override
    public void updateTag(TagDataReq tagDataReq) {
        TagUpdateEntity tagUpdateEntity = ObjectConvertUtils.convert(tagDataReq, TagUpdateEntity.class);
        tagService.updateTag(tagUpdateEntity);
    }

    @DeleteMapping("/tag")
    @Override
    public void deleteTag(Integer id) {
        tagService.deleteTag(id);
    }

    @DeleteMapping("/tag/batch")
    @Override
    public void deleteTag(Integer[] ids) {
        tagService.deleteTag(ids);
    }

    @GetMapping("/tag/page")
    @Override
    public PageData<TagDetailRes> getPageOfTag(PageParams pageParams, TagQueryReq tagQueryReq) {
        TagQueryEntity tagQueryEntity = ObjectConvertUtils.convert(tagQueryReq, TagQueryEntity.class);
        PageData<TagAggregate> pageOfTagDetails = tagService.getPageOfTags(pageParams, tagQueryEntity);
        List<TagDetailRes> tagDetailResList = ObjectConvertUtils.convertList(pageOfTagDetails.getData(), TagDetailRes.class);
        return PageData.<TagDetailRes>builder()
                .number(pageOfTagDetails.getNumber())
                .size(pageOfTagDetails.getSize())
                .total(pageOfTagDetails.getTotal())
                .data(tagDetailResList)
                .build();
    }

    @GetMapping("/statistics/number")
    @Override
    public NumberStatisticsRes getStatistics() {
        Long articleQuantity = articleService.getArticleQuantity();
        Long categoryQuantity = categoryService.getCategoryQuantity();
        Long tagQuantity = tagService.getTagQuantity();
        return NumberStatisticsRes.builder()
                .articleCount(articleQuantity)
                .categoryCount(categoryQuantity)
                .tagCount(tagQuantity)
                .build();
    }
}
