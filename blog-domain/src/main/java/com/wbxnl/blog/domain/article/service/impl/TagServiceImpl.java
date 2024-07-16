package com.wbxnl.blog.domain.article.service.impl;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.TagAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.TagVo;
import com.wbxnl.blog.domain.article.repository.ITagRepository;
import com.wbxnl.blog.domain.article.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:18
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements ITagService {

    private final ITagRepository tagRepository;

    @Override
    public TagEntity addArticleTag(TagVo tagVo) {
        return tagRepository.addTag(tagVo);
    }

    @Override
    public boolean deleteArticleTag(Integer id) {
        return tagRepository.deleteTag(id);
    }

    @Override
    public boolean deleteArticleTag(Integer[] ids) {
        return tagRepository.deleteTag(ids);
    }

    @Override
    public boolean updateArticleTag(TagUpdateEntity tagUpdateEntity) {
        return tagRepository.updateTag(tagUpdateEntity);
    }

    @Override
    public TagEntity getTag(Integer id) {
        return tagRepository.getTag(id);
    }

    @Override
    public List<TagSimpleInfoEntity> getTags(Integer[] ids) {
        return tagRepository.getTags(ids);
    }

    @Override
    public List<TagSimpleInfoEntity> getAllTags() {
        return tagRepository.getAllTags();
    }

    @Override
    public PageData<TagAggregate> getPageTags(PageParams pageParams, TagQueryEntity tagQueryEntity) {
        return tagRepository.getPageTags(pageParams, tagQueryEntity);
    }

    @Override
    public boolean linkArticleAndTag(ArticleAndTagLinkEntity articleAndTagLinkEntity) {
        return tagRepository.linkArticleAndTag(articleAndTagLinkEntity);
    }

    @Override
    public boolean linkArticleAndTag(List<ArticleAndTagLinkEntity> articleAndTagLinkEntities) {
        return tagRepository.linkArticleAndTag(articleAndTagLinkEntities);
    }

    @Override
    public boolean unlinkArticleAndTag(Integer id) {
        return tagRepository.unlinkArticleAndTag(id);
    }

    @Override
    public boolean unlinkArticleAndTag(Integer[] ids) {
        return tagRepository.unlinkArticleAndTag(ids);
    }

    @Override
    public List<TagAggregate> getAllTagsByUser() {
        return tagRepository.getAllTagsByUser();
    }

    @Override
    public Long getTagQuantityByUser() {
        return tagRepository.getTagQuantityByUser();
    }
}
