package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.*;
import com.wbxnl.blog.api.admin.model.res.*;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/1 17:20
 */
public interface IArticleService {
    /**
     * 发布文章
     * @param articleDataReq 文章数据
     * @return 分类id和key
     */
    KeyData publishArticle(ArticleDataReq articleDataReq);

    /**
     * 插入或更新文章草稿
     * @param articleDataReq 文章数据
     * @return 文章id和key
     */
    KeyData addOrUpdateArticleDraft(ArticleDataReq articleDataReq);

    /**
     * 更新文章置顶状态
     * @param id 文章id
     * @param top 文章置顶状态
     */
    void updateArticleTop(Integer id, Integer top);

    /**
     * 更新文章基本信息，除开内容
     * @param articleBasicDataReq 文章数据
     */
    void updateArticleBasicInfo(ArticleBasicDataReq articleBasicDataReq);

    /**
     * 删除文章
     * @param id 文章id
     * @return 是否成功
     */
    void deleteArticle(Integer id);

    /**
     * 批量删除文章
     * @param ids 文章id
     */
    void deleteArticle(Integer[] ids);

    /**
     * 获取文章列表
     * @param pageParams 分页参数
     * @param articleQueryReq 查询条件
     * @return 文章列表
     */
    PageData<ArticleInfoRes> getPageOfArticles(PageParams pageParams, ArticleQueryReq articleQueryReq);

    /**
     * 获取文章归档
     * @param pageParams 分页参数
     * @param articleQueryReq 查询条件
     * @return 文章归档
     */
    PageData<ArticleInfoRes> getArchive(PageParams pageParams, ArticleQueryReq articleQueryReq);

    /**
     * 获取文章详情
     * @param id 文章id
     * @return 文章详情信息
     */
    ArticleDetailRes getArticleDetail(Integer id);

    /**
     * 获取最近一个月文章统计信息
     * @return 文章统计信息
     */
    List<KeyAndValueRes> getArticleStatisticsOfLastMonth();

    /**
     * 添加文章分类
     * @param categoryDataReq 分类数据
     * @return 分类id和key
     */
    KeyData addCategory(CategoryDataReq categoryDataReq);

    /**
     * 更新文章分类
     * @param categoryDataReq 分类数据
     */
    void updateCategory(CategoryDataReq categoryDataReq);

    /**
     * 更新文章分类状态
     * @param id 分类id
     * @param status 分类状态
     */
    void updateCategoryStatus(Integer id, String status);

    /**
     * 删除分类
     * @param id 分类id
     */
    void deleteCategory(Integer id);

    /**
     * 批量删除分类
     * @param ids 分类id
     */
    void deleteCategory(Integer[] ids);

    /**
     * 获取分类列表
     * @param pageParams 分页参数
     * @param categoryQueryReq 查询条件
     * @return 分类列表
     */
    PageData<CategoryDetailRes> getPageOfCategory(PageParams pageParams, CategoryQueryReq categoryQueryReq);

    /**
     * 添加标签
     * @param tagDataReq 标签数据
     * @return 标签id和key
     */
    KeyData addTag(TagDataReq tagDataReq);

    /**
     * 更新标签
     * @param tagDataReq 标签数据
     */
    void updateTag(TagDataReq tagDataReq);

    /**
     * 删除标签
     * @param id 标签id
     */
    void deleteTag(Integer id);

    /**
     * 批量删除标签
     * @param ids 标签id
     */
    void deleteTag(Integer[] ids);

    /**
     * 获取标签列表
     * @param pageParams 分页参数
     * @param tagQueryReq 查询条件
     * @return 标签列表
     */
    PageData<TagDetailRes> getPageOfTag(PageParams pageParams, TagQueryReq tagQueryReq);

    /**
     * 获取文章、分类、标签等数量统计信息
     * @return 统计信息
     */
    NumberStatisticsRes getStatistics();
}
