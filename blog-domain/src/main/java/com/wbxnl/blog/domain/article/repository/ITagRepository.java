package com.wbxnl.blog.domain.article.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.TagAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.TagVo;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:28
 */
public interface ITagRepository {
    TagEntity addTag(TagVo tagVo);

    boolean deleteTag(Integer id);

    boolean deleteTag(Integer[] ids);

    boolean updateTag(TagUpdateEntity tagUpdateEntity);

    boolean updateTagStatus(Integer id, String status);

    List<TagSimpleInfoEntity> getAllTags();

    List<TagAggregate> getAllTagsByUser();

    TagEntity getTag(Integer id);

    Long getTagQuantityByUser();

    List<TagSimpleInfoEntity> getTags(Integer[] ids);

    PageData<TagAggregate> getPageTags(PageParams pageParams, TagQueryEntity tagQueryEntity);

    boolean linkArticleAndTag(ArticleAndTagLinkEntity articleAndTagLinkEntity);

    boolean linkArticleAndTag(List<ArticleAndTagLinkEntity> articleAndTagLinkEntities);

    boolean unlinkArticleAndTag(Integer id);

    boolean unlinkArticleAndTag(Integer[] ids);
}
