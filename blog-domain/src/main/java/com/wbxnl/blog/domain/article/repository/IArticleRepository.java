package com.wbxnl.blog.domain.article.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleArchiveAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.ArticleVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:29
 */
public interface IArticleRepository {
    ArticleEntity addArticle(ArticleVo articleVo);
    boolean deleteArticle(Integer id);
    boolean deleteArticle(Integer[] ids);
    boolean updateArticle(ArticleUpdateEntity articleUpdateEntity);
    boolean updateArticleBasicInfo(ArticleBasicUpdateEntity articleBasicUpdateEntity);
    boolean updateArticleStatus(Integer id, String status);
    boolean updateArticleTop(Integer id, Integer top);
    ArticleEntity getArticle(Integer id);
    ArticleAggregate getArticleDetail(Integer id,boolean isVisitor);
    Long getArticleQuantityByUser();
    PageData<ArticleAggregate> getPageArticleDetails(PageParams pageParams, ArticleQueryEntity articleQueryEntity);
    PageData<ArticleArchiveAggregate> getPageArticleDetailsOfArchive(PageParams pageParams, boolean isReverseOrder, boolean isVisitor);
    PageData<ArticleAggregate> getPageArticleDetailsOfVisitor(PageParams pageParams, ArticleQueryByVisitorEntity articleQueryByVisitorEntity);

}
