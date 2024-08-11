package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.enums.ArticleStatusEnum;
import com.wbxnl.blog.common.enums.ArticleTypeEnum;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleArchiveAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.ArticleDraftVo;
import com.wbxnl.blog.domain.article.model.vo.ArticleHandleVo;
import com.wbxnl.blog.domain.article.repository.IArticleRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.ArticleDao;
import com.wbxnl.blog.infrastructure.persistent.dao.ArticleTagDao;
import com.wbxnl.blog.infrastructure.persistent.dao.CategoryDao;
import com.wbxnl.blog.infrastructure.persistent.dao.TagDao;
import com.wbxnl.blog.infrastructure.persistent.po.Article;
import com.wbxnl.blog.infrastructure.persistent.po.ArticleTag;
import com.wbxnl.blog.infrastructure.persistent.po.Category;
import com.wbxnl.blog.infrastructure.persistent.po.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/1 11:41
 */
@Service
@RequiredArgsConstructor
public class ArticleRepository implements IArticleRepository {

    private final ArticleDao articleDao;

    private final CategoryDao categoryDao;

    private final TagDao tagDao;

    private final ArticleTagDao articleTagDao;

    @Override
    public ArticleEntity addArticle(ArticleHandleVo articleHandleVo) {
        Article article = ObjectConvertUtils.convert(articleHandleVo, Article.class);
        int insert = articleDao.insert(article);
        if(insert <= 0){
            return null;
        }
        return ObjectConvertUtils.convert(article, ArticleEntity.class);
    }

    @Override
    public ArticleEntity updateArticleDraft(ArticleDraftVo articleDraftVo) {
        Article article = ObjectConvertUtils.convert(articleDraftVo, Article.class);
        articleDao.updateById(article);
        return ObjectConvertUtils.convert(article, ArticleEntity.class);
    }

    @Override
    public ArticleEntity addArticleDraft(ArticleDraftVo articleDraftVo) {
        Article article = ObjectConvertUtils.convert(articleDraftVo, Article.class);
        articleDao.insert(article);
        return ObjectConvertUtils.convert(article, ArticleEntity.class);
    }

    @Override
    public boolean deleteArticle(Integer id) {
        return articleDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteArticle(Integer[] ids) {
        return articleDao.deleteByIds(List.of(ids)) > 0;
    }

    @Override
    public boolean updateArticle(ArticleHandleVo articleHandleVo) {
        Article article = ObjectConvertUtils.convert(articleHandleVo, Article.class);
        return articleDao.updateById(article) > 0;
    }

    @Override
    public boolean updateArticleBasicInfo(ArticleBasicUpdateEntity articleBasicUpdateEntity) {
        Article article = ObjectConvertUtils.convert(articleBasicUpdateEntity, Article.class);
        return articleDao.updateById(article) > 0;
    }

    @Override
    public boolean updateArticleStatus(Integer id, String status) {
        LambdaUpdateWrapper<Article> articleLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        articleLambdaUpdateWrapper
                .eq(Article::getId, id)
                .set(Article::getStatus, status);
        return articleDao.update(null, articleLambdaUpdateWrapper) > 0;
    }

    @Override
    public boolean updateArticleTop(Integer id, Integer top) {
        LambdaUpdateWrapper<Article> articleLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        articleLambdaUpdateWrapper
                .eq(Article::getId, id)
                .set(Article::getTop, top);
        return articleDao.update(null, articleLambdaUpdateWrapper) > 0;
    }

    @Override
    public ArticleAggregate getArticleDetail(Integer id) {
        // 查询文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper
                .eq(Article::getId, id);
        Article article = articleDao.selectOne(articleLambdaQueryWrapper);
        // 查询分类
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper
                .select(Category::getId, Category::getName, Category::getDescription, Category::getCategoryKey)
                .eq(Category::getCategoryKey, article.getCategoryKey());
        Category category = categoryDao.selectOne(categoryLambdaQueryWrapper);
        CategorySimpleInfoEntity categorySimpleInfoEntity = ObjectConvertUtils
                .convert(category, CategorySimpleInfoEntity.class);
        // 查询标签
        List<TagSimpleInfoEntity> tagList = getTagList(article.getArticleKey());
        // 聚合模型
        ArticleAggregate articleAggregate = ObjectConvertUtils.convert(article, ArticleAggregate.class);
        articleAggregate.setCategory(categorySimpleInfoEntity);
        articleAggregate.setTags(tagList);
        return articleAggregate;
    }

    @Override
    public ArticleAggregate getArticleDetailByUser(Integer id) {
        // 查询文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper
                .eq(Article::getId, id)
                .ne(Article::getStatus, ArticleStatusEnum.PRIVATE.getStatus())
                .ne(Article::getType, ArticleTypeEnum.DRAFT.getType());
        Article article = articleDao.selectOne(articleLambdaQueryWrapper);
        if (article == null || article.getCategoryKey() == null) {
            return null;
        }
        // 查询分类
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper
                .select(Category::getId, Category::getName, Category::getDescription, Category::getCategoryKey)
                .eq(Category::getCategoryKey, article.getCategoryKey())
                .eq(Category::getHidden, 0);
        Category category = categoryDao.selectOne(categoryLambdaQueryWrapper);
        if (category == null) {
            return null;
        }
        CategorySimpleInfoEntity categorySimpleInfoEntity = ObjectConvertUtils.convert(category, CategorySimpleInfoEntity.class);
        // 查询标签
        List<TagSimpleInfoEntity> tagList = getTagList(article.getArticleKey());
        // 聚合模型
        ArticleAggregate articleAggregate = ObjectConvertUtils.convert(article, ArticleAggregate.class);
        articleAggregate.setCategory(categorySimpleInfoEntity);
        articleAggregate.setTags(tagList);
        return articleAggregate;
    }

    /**
     * 获取文章标签信息
     *
     * @param articleKey 文章key
     * @return 标签
     */
    private List<TagSimpleInfoEntity> getTagList(String articleKey) {
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper
                .eq(ArticleTag::getArticleKey, articleKey);
        List<ArticleTag> articleTags = articleTagDao.selectList(articleTagLambdaQueryWrapper);
        List<String> tagKeys = articleTags.stream().map(ArticleTag::getTagKey).collect(Collectors.toList());
        LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tagLambdaQueryWrapper
                .select(Tag::getId, Tag::getTagKey, Tag::getName, Tag::getDescription)
                .in(Tag::getTagKey, tagKeys);
        List<Tag> tags = tagDao.selectList(tagLambdaQueryWrapper);
        return ObjectConvertUtils.convertList(tags, TagSimpleInfoEntity.class);
    }

    @Override
    public Long getArticleQuantityByUser() {
        // 查询文章数量
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper
                .ne(Article::getStatus, ArticleStatusEnum.PRIVATE.getStatus())
                .ne(Article::getType, ArticleTypeEnum.DRAFT.getType());
        return articleDao.selectCount(articleLambdaQueryWrapper);
    }

    @Override
    public PageData<ArticleAggregate> getPageOfArticleDetails(PageParams pageParams, ArticleQueryEntity articleQueryEntity) {
        List<ArticleAggregate> list=articleDao.getPageOfArticleDetails(pageParams, articleQueryEntity);
        articleDao.getCount(articleQueryEntity);
        return PageData.<ArticleAggregate>builder()
                .data(list)
                .number(pageParams.getNumber())
                .size(pageParams.getSize())
                .total(articleDao.getCount(articleQueryEntity))
                .build();
    }

    @Override
    public PageData<ArticleArchiveAggregate> getPageOfArticleDetailsOfArchive(PageParams pageParams, boolean isReverseOrder) {
        // TODO 待实现SQL复杂查询
        return null;
    }

    @Override
    public PageData<ArticleAggregate> getPageOfArticleDetailsByUser(PageParams pageParams, ArticleQueryByVisitorEntity articleQueryByVisitorEntity) {
        // TODO 待实现SQL复杂查询
        return null;
    }

    @Override
    public Long getArticleQuantity() {
        return articleDao.selectCount(null);
    }
}
