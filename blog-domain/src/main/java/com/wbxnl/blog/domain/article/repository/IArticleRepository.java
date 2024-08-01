package com.wbxnl.blog.domain.article.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleArchiveAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.ArticleDraftVo;
import com.wbxnl.blog.domain.article.model.vo.ArticleHandleVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:29
 */
public interface IArticleRepository {
    ArticleEntity addArticle(ArticleHandleVo articleHandleVo);

    ArticleEntity updateArticleDraft(ArticleDraftVo articleDraftVo);

    ArticleEntity addArticleDraft(ArticleDraftVo articleDraftVo);

    boolean deleteArticle(Integer id);

    boolean deleteArticle(Integer[] ids);

    boolean updateArticle(ArticleHandleVo articleHandleVo);

    boolean updateArticleBasicInfo(ArticleBasicUpdateEntity articleBasicUpdateEntity);

    boolean updateArticleStatus(Integer id, String status);

    boolean updateArticleTop(Integer id, Integer top);

    ArticleEntity getArticle(Integer id);

    ArticleAggregate getArticleDetail(Integer id);

    ArticleAggregate getArticleDetailByUser(Integer id);

    Long getArticleQuantityByUser();

    PageData<ArticleAggregate> getPageArticleDetails(PageParams pageParams, ArticleQueryEntity articleQueryEntity);

    PageData<ArticleArchiveAggregate> getPageArticleDetailsOfArchive(PageParams pageParams, boolean isReverseOrder, boolean isVisitor);

    PageData<ArticleAggregate> getPageArticleDetailsByUser(PageParams pageParams, ArticleQueryByVisitorEntity articleQueryByVisitorEntity);

}
