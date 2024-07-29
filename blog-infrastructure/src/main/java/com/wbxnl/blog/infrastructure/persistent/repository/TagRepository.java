package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.TagAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.TagHandleVo;
import com.wbxnl.blog.domain.article.repository.ITagRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.ArticleDao;
import com.wbxnl.blog.infrastructure.persistent.dao.TagDao;
import com.wbxnl.blog.infrastructure.persistent.po.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/29 17:36
 */
@Service
@RequiredArgsConstructor
public class TagRepository implements ITagRepository {

    private final TagDao tagDao;

    private final ArticleDao articleDao;

    @Override
    public TagEntity addTag(TagHandleVo tagHandleVo) {
        Tag tag = ObjectConvertUtils.convert(tagHandleVo, Tag.class);
        tagDao.insert(tag);
        return ObjectConvertUtils.convert(tag, TagEntity.class);
    }

    @Override
    public boolean deleteTag(Integer id) {
        return tagDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteTag(Integer[] ids) {
        return tagDao.deleteBatchIds(List.of(ids)) > 0;
    }

    @Override
    public boolean updateTag(TagUpdateEntity tagUpdateEntity) {
        Tag tag = ObjectConvertUtils.convert(tagUpdateEntity, Tag.class);
        return tagDao.updateById(tag) > 0;
    }

    @Override
    public boolean updateTagStatus(Integer id, String status) {
        LambdaUpdateWrapper<Tag> updateWrapper = new LambdaUpdateWrapper<Tag>()
                .eq(Tag::getId, id)
                .set(Tag::getHidden, status);
        return tagDao.update(null, updateWrapper) > 0;
    }

    @Override
    public List<TagSimpleInfoEntity> getAllTags() {
        return List.of();
    }

    @Override
    public List<TagAggregate> getAllTagsByUser() {
        return List.of();
    }

    @Override
    public TagEntity getTag(Integer id) {
        return null;
    }

    @Override
    public TagEntity getTag(String tagKey) {
        return null;
    }

    @Override
    public Long getTagQuantityByUser() {
        return null;
    }

    @Override
    public List<TagSimpleInfoEntity> getTags(Integer[] ids) {
        return List.of();
    }

    @Override
    public PageData<TagAggregate> getPageTags(PageParams pageParams, TagQueryEntity tagQueryEntity) {
        return null;
    }

    @Override
    public boolean linkArticleAndTag(ArticleAndTagLinkEntity articleAndTagLinkEntity) {
        return false;
    }

    @Override
    public boolean linkArticleAndTag(List<ArticleAndTagLinkEntity> articleAndTagLinkEntities) {
        return false;
    }

    @Override
    public boolean unlinkArticleAndTag(String articleKey) {
        return false;
    }

    @Override
    public boolean unlinkArticleAndTag(String articleKey, String tagKey) {
        return false;
    }

    @Override
    public TagEntity getTagByName(String name) {
        return null;
    }

    @Override
    public List<TagSimpleInfoEntity> getTagList(String articleKey) {
        return List.of();
    }
}
