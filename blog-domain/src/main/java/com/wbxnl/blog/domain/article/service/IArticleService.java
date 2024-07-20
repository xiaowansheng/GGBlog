package com.wbxnl.blog.domain.article.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleArchiveAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.ArticleDraftVo;
import com.wbxnl.blog.domain.article.model.vo.ArticleVo;

import java.util.List;

/**
 * description: 描述该文件的功能或目的.
 *
 * @author xiaowansheng
 * @since 2024/7/14 1:52
 */
public interface IArticleService {
    /**
     * 添加文章
     *
     * @param articleVo 文章信息
     * @return 添加后的文章信息
     */
    ArticleEntity addArticle(ArticleVo articleVo);

    /**
     * 保存草稿
     * @param articleDraftVo 文章内容
     * @return 文章实体
     */
    ArticleEntity saveArticleDraft(ArticleDraftVo articleDraftVo);

    /**
     * 删除文章
     *
     * @param id 文章id
     * @return 是否删除成功
     */
    boolean deleteArticle(Integer id);

    /**
     * 批量删除文章
     *
     * @param ids 文章id
     * @return 是否删除成功
     */
    boolean deleteArticle(Integer[] ids);

    /**
     * 更新文章
     *
     * @param articleUpdateEntity 更新的文章信息
     * @return 是否更新成功
     */
    boolean updateArticle(ArticleUpdateEntity articleUpdateEntity);

    /**
     * 更新文章基本信息
     *
     * @param articleBasicUpdateEntity 文章基本信息
     * @return 是否更新成功
     */
    boolean updateArticleBasicInfo(ArticleBasicUpdateEntity articleBasicUpdateEntity);

    /**
     * 更新文章状态
     *
     * @param id     文章id
     * @param status 文章状态
     * @return 是否更新成功
     */
    boolean updateArticleStatus(Integer id, String status);

    /**
     * 更新文章置顶
     *
     * @param id  文章id
     * @param top 是否置顶
     * @return 是否更新成功
     */
    boolean updateArticleTop(Integer id, Integer top);

    /**
     * 获取文章信息
     * @param id 文章id
     * @return 文章信息
     */
    ArticleEntity getArticle(Integer id);

    /**
     * 获取文章信息
     *
     * @param id 文章id
     * @return 文章信息
     */
    ArticleAggregate getArticleDetail(Integer id,boolean isVisitor);

    /**
     * 获取文章列表
     *
     * @param pageParams         分页参数
     * @param articleQueryEntity 查询参数
     * @return 文章列表
     */
    PageData<ArticleAggregate> getPageArticleDetails(PageParams pageParams, ArticleQueryEntity articleQueryEntity);


    /**
     * 获取文章归档列表
     *
     * @param pageParams     分页参数
     * @param isReverseOrder 是否倒序
     * @param isVisitor      是否访客访问
     * @return 文章列表
     */
    PageData<ArticleArchiveAggregate> getPageArticleDetailsOfArchive(PageParams pageParams, boolean isReverseOrder, boolean isVisitor);

    /**
     * 访客获取文章列表
     * 只能获取开放的、公开的文章、已经发布的文章
     * 并且根据时间排序
     *
     * @param pageParams                  分页参数
     * @param articleQueryByVisitorEntity 用户查询参数
     * @return 文章列表
     */
    PageData<ArticleAggregate> getPageArticleDetailsOfVisitor(PageParams pageParams, ArticleQueryByVisitorEntity articleQueryByVisitorEntity);

    /**
     * 用户查询文章数量
     *
     * @return 文章数量
     */
    Long getArticleQuantityByUser();
}
