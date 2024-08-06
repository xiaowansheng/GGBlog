package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.enums.ArticleStatusEnum;
import com.wbxnl.blog.common.enums.ArticleTypeEnum;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.TagAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.TagSimpleAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.TagHandleVo;
import com.wbxnl.blog.domain.article.repository.ITagRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.ArticleDao;
import com.wbxnl.blog.infrastructure.persistent.dao.ArticleTagDao;
import com.wbxnl.blog.infrastructure.persistent.dao.CategoryDao;
import com.wbxnl.blog.infrastructure.persistent.dao.TagDao;
import com.wbxnl.blog.infrastructure.persistent.po.Article;
import com.wbxnl.blog.infrastructure.persistent.po.ArticleTag;
import com.wbxnl.blog.infrastructure.persistent.po.Category;
import com.wbxnl.blog.infrastructure.persistent.po.Tag;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.executor.BatchResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private final CategoryDao categoryDao;

    private final ArticleDao articleDao;

    private final ArticleTagDao articleTagDao;

    @Override
    public TagEntity addTag(TagHandleVo tagHandleVo) {
        Tag tag = ObjectConvertUtils.convert(tagHandleVo, Tag.class);
        int insert = tagDao.insert(tag);
        if (insert <= 0) {
            return null;
        }
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
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getTagKey, Tag::getName, Tag::getDescription);
        List<Tag> tags = tagDao.selectList(queryWrapper);
        return ObjectConvertUtils.convertList(tags, TagSimpleInfoEntity.class);
    }

    @Override
    public List<TagSimpleAggregate> getAllTagsByUser() {
        // TODO 没啥访问量，以后在优化
        // 获取标签数据
        List<Tag> tags = tagDao.selectList(null);
        List<TagSimpleAggregate> tagSimpleAggregates = ObjectConvertUtils.convertList(tags, TagSimpleAggregate.class);
        // 查询所有文章基本信息，去除私密和草稿
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper
                .select(Article::getArticleKey, Article::getId, Article::getCategoryKey)
                .ne(Article::getStatus, ArticleStatusEnum.PRIVATE.getStatus())
                .ne(Article::getType, ArticleTypeEnum.DRAFT.getType());
        List<Article> articles = articleDao.selectList(articleLambdaQueryWrapper);
        // 查询所有的文章分类，去除隐藏的
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper
                .select(Category::getId, Category::getCategoryKey)
                .eq(Category::getHidden, 0);
        List<Category> categories = categoryDao.selectList(categoryLambdaQueryWrapper);
        // 转为map
        HashMap<String, Category> categoryMap = new HashMap<>();
        categories.forEach(category -> categoryMap.put(category.getCategoryKey(), category));
        // 过滤不显示分类的文章
        Iterator<Article> iterator = articles.iterator();
        while (iterator.hasNext()) {
            Article article = iterator.next();
            if (categoryMap.get(article.getCategoryKey()) == null) {
                iterator.remove();
            }
        }
        // 文章转为map
        HashMap<String, Article> articleMap = new HashMap<>();
        articles.forEach(article -> articleMap.put(article.getArticleKey(), article));
        // 查询标签文章对应关系
        List<ArticleTag> articleTags = articleTagDao.selectList(null);
        // 统计数量
        tagSimpleAggregates.forEach(tagSimpleAggregate -> {
            int count = 0;
            for (ArticleTag articleTag : articleTags) {
                if (articleTag.getTagKey().equals(tagSimpleAggregate.getTagKey())) {
                    if (articleMap.get(articleTag.getArticleKey()) != null) {
                        count++;
                    }

                }
            }
            tagSimpleAggregate.setArticleCount(count);
        });
        return tagSimpleAggregates;
    }

    @Override
    public TagEntity getTag(Integer id) {
        Tag tag = tagDao.selectById(id);
        return ObjectConvertUtils.convert(tag, TagEntity.class);
    }

    @Override
    public TagEntity getTag(String tagKey) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<Tag>().eq(Tag::getTagKey, tagKey);
        Tag tag = tagDao.selectOne(queryWrapper);
        return ObjectConvertUtils.convert(tag, TagEntity.class);
    }

    @Override
    public Long getTagQuantityByUser() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<Tag>().ne(Tag::getHidden, 1);
        return tagDao.selectCount(queryWrapper);
    }

    @Override
    public List<TagSimpleInfoEntity> getTags(Integer[] ids) {
        List<Tag> tags = tagDao.selectBatchIds(List.of(ids));
        return ObjectConvertUtils.convertList(tags, TagSimpleInfoEntity.class);
    }

    @Override
    public PageData<TagAggregate> getPageTags(PageParams pageParams, TagQueryEntity tagQueryEntity) {
        // 分页查询标签数量
        Page<Tag> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tagLambdaQueryWrapper
                .like(StringUtils.isNotBlank(tagQueryEntity.getTagKey()), Tag::getTagKey, tagQueryEntity.getTagKey())
                .like(StringUtils.isNotBlank(tagQueryEntity.getName()), Tag::getName, tagQueryEntity.getName())
                .like(StringUtils.isNotBlank(tagQueryEntity.getDescription()), Tag::getDescription, tagQueryEntity.getDescription())
                .between(tagQueryEntity.getBeginCreateTime() != null && tagQueryEntity.getEndCreateTime() != null, Tag::getCreateTime, tagQueryEntity.getBeginCreateTime(), tagQueryEntity.getEndCreateTime());
        // 查询标签
        Page<Tag> selectedPage = tagDao.selectPage(page, tagLambdaQueryWrapper);
        List<TagAggregate> tagAggregates = ObjectConvertUtils.convertList(selectedPage.getRecords(), TagAggregate.class);
        // 查询标签对应的文章数量
        List<String> tagKeys = tagAggregates.stream().map(tagAggregate -> tagAggregate.getTagKey()).collect(Collectors.toList());
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper
                .in(ArticleTag::getTagKey, tagKeys);
        List<ArticleTag> articleTags = articleTagDao.selectList(articleTagLambdaQueryWrapper);
        // 统计数量
        tagAggregates.forEach(tagAggregate -> {
            int count = 0;
            for (ArticleTag articleTag : articleTags) {
                if (articleTag.getTagKey().equals(tagAggregate.getTagKey())) {
                    count++;
                }
            }
            tagAggregate.setArticleCount(count);
        });
        return PageUtils.convertPageData(pageParams.getNumber(), pageParams.getSize(), selectedPage.getTotal(), tagAggregates);
    }

    @Override
    public boolean linkArticleAndTag(ArticleAndTagLinkEntity articleAndTagLinkEntity) {
        ArticleTag articleTag = ObjectConvertUtils.convert(articleAndTagLinkEntity, ArticleTag.class);
        return articleTagDao.insert(articleTag) > 0;
    }

    @Override
    public boolean linkArticleAndTag(List<ArticleAndTagLinkEntity> articleAndTagLinkEntities) {
        List<ArticleTag> articleTags = ObjectConvertUtils.convertList(articleAndTagLinkEntities, ArticleTag.class);
        List<BatchResult> insert = articleTagDao.insert(articleTags, articleTags.size());
        return insert.size() == articleTags.size();
    }

    @Override
    public boolean unlinkArticleAndTag(String articleKey) {
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper
                .eq(ArticleTag::getArticleKey, articleKey);
        return articleTagDao.delete(articleTagLambdaQueryWrapper) > 0;
    }

    @Override
    public boolean unlinkArticleAndTag(String articleKey, String tagKey) {
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper
                .eq(ArticleTag::getArticleKey, articleKey)
                .eq(ArticleTag::getTagKey, tagKey);
        return articleTagDao.delete(articleTagLambdaQueryWrapper) > 0;
    }

    @Override
    public TagEntity getTagByName(String name) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<Tag>().eq(Tag::getName, name);
        Tag tag = tagDao.selectOne(queryWrapper);
        return ObjectConvertUtils.convert(tag, TagEntity.class);
    }

    @Override
    public List<String> getTagKeyList(String articleKey) {
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper
                .select(ArticleTag::getTagKey)
                .eq(ArticleTag::getArticleKey, articleKey);
        List<ArticleTag> articleTags = articleTagDao.selectList(articleTagLambdaQueryWrapper);
        return articleTags.stream().map(ArticleTag::getTagKey).collect(Collectors.toList());
    }

    @Override
    public Long getTagQuantity() {
        return tagDao.selectCount(null);
    }
}
