package com.wbxnl.blog.domain.article.service.impl;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleArchiveAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.ArticleVo;
import com.wbxnl.blog.domain.article.repository.IArticleRepository;
import com.wbxnl.blog.domain.article.repository.ICategoryRepository;
import com.wbxnl.blog.domain.article.repository.ITagRepository;
import com.wbxnl.blog.domain.article.service.IArticleService;
import lombok.RequiredArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:17
 */
@RequiredArgsConstructor
public class ArticleServiceImpl implements IArticleService {

    private final IArticleRepository articleRepository;

    @Override
    public ArticleEntity addArticle(ArticleVo articleVo) {
        return articleRepository.addArticle(articleVo);
    }

    @Override
    public boolean deleteArticle(Integer id) {
        return articleRepository.deleteArticle(id);
    }

    @Override
    public boolean deleteArticle(Integer[] ids) {
        return articleRepository.deleteArticle(ids);
    }

    @Override
    public boolean updateArticle(ArticleUpdateEntity articleUpdateEntity) {
        return articleRepository.updateArticle(articleUpdateEntity);
    }

    @Override
    public boolean updateArticleBasicInfo(ArticleBasicUpdateEntity articleBasicUpdateEntity) {
        return articleRepository.updateArticleBasicInfo(articleBasicUpdateEntity);
    }

    @Override
    public boolean updateArticleStatus(Integer id, String status) {
        return articleRepository.updateArticleStatus(id, status);
    }

    @Override
    public boolean updateArticleTop(Integer id, Integer top) {
        return articleRepository.updateArticleTop(id, top);
    }

    @Override
    public ArticleEntity getArticle(Integer id) {
        return articleRepository.getArticle(id);
    }

    @Override
    public ArticleAggregate getArticleDetail(Integer id, boolean isVisitor) {
        return articleRepository.getArticleDetail(id, isVisitor);
    }

    @Override
    public PageData<ArticleAggregate> getPageArticleDetails(PageParams pageParams, ArticleQueryEntity articleQueryEntity) {
        return articleRepository.getPageArticleDetails(pageParams, articleQueryEntity);
    }

    @Override
    public PageData<ArticleArchiveAggregate> getPageArticleDetailsOfArchive(PageParams pageParams, boolean isReverseOrder, boolean isVisitor) {
        return articleRepository.getPageArticleDetailsOfArchive(pageParams, isReverseOrder, isVisitor);
    }

    @Override
    public PageData<ArticleAggregate> getPageArticleDetailsOfVisitor(PageParams pageParams, ArticleQueryByVisitorEntity articleQueryByVisitorEntity) {
        return articleRepository.getPageArticleDetailsOfVisitor(pageParams, articleQueryByVisitorEntity);
    }

    @Override
    public Long getArticleQuantityByUser() {
        return articleRepository.getArticleQuantityByUser();
    }
}
