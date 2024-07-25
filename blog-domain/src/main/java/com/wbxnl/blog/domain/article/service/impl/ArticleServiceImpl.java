package com.wbxnl.blog.domain.article.service.impl;

import com.wbxnl.blog.common.enums.ArticleTypeEnum;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.utils.UuidUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.ArticleArchiveAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.*;
import com.wbxnl.blog.domain.article.repository.IArticleRepository;
import com.wbxnl.blog.domain.article.service.IArticleService;
import com.wbxnl.blog.domain.article.service.ICategoryService;
import com.wbxnl.blog.domain.article.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:17
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements IArticleService {

    private final IArticleRepository articleRepository;

    private final ICategoryService categoryService;

    private final ITagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleEntity addArticle(ArticleVo articleVo) {
        ArticleHandleVo articleHandleVo = ObjectConvertUtils.convert(articleVo, ArticleHandleVo.class);
        articleHandleVo.setArticleKey(UuidUtils.uuid());
        ArticleVo.CategoryVo articleVoCategory = articleVo.getCategory();
        // 1、处理分类信息
        // 检查是否存在这个分类，存在则直接设置分类，不存在则插入后设置分类
        if (StringUtils.hasText(articleVoCategory.getCategoryKey())) {
            // 检查是否存在
            CategoryEntity category = categoryService.getCategory(articleVoCategory.getCategoryKey());
            if (category != null) {
                articleHandleVo.setCategoryKey(articleVoCategory.getCategoryKey());
            }
        } else {
            // 检查要插入的分类是否已经存在
            CategoryEntity categoryEntity = categoryService.getCategoryByName(articleVoCategory.getName());
            if (categoryEntity != null) {
                articleHandleVo.setCategoryKey(categoryEntity.getCategoryKey());
            } else {

                CategoryVo categoryVo = new CategoryVo();
                categoryVo.setName(articleVoCategory.getName());
                categoryEntity = categoryService.addArticleCategory(categoryVo);
                articleHandleVo.setCategoryKey(categoryEntity.getCategoryKey());
            }
        }
        // 2、处理标签信息
        List<ArticleVo.TagVo> articleVoTags = articleVo.getTags();
        articleVoTags.forEach(articleVoTag -> {
            String tagKey = articleVoTag.getTagKey();
            TagEntity tagEntity = null;
            // 检查是否存在标签
            if (StringUtils.hasText(tagKey)) {
                tagEntity = tagService.getTag(tagKey);
            } else {
                TagVo tagVo = new TagVo();
                tagVo.setName(articleVoTag.getName());
                tagEntity = tagService.addArticleTag(tagVo);
            }
            // 给文章添加标签信息
            if (tagEntity != null) {
                ArticleAndTagLinkEntity articleAndTagLinkEntity = new ArticleAndTagLinkEntity();
                articleAndTagLinkEntity.setArticleKey(articleHandleVo.getArticleKey());
                articleAndTagLinkEntity.setTagKey(tagEntity.getTagKey());
                tagService.linkArticleAndTag(articleAndTagLinkEntity);
            }
        });
        // 3、插入文章信息
        return articleRepository.addArticle(articleHandleVo);
    }

    @Override
    public ArticleEntity saveArticleDraft(ArticleDraftVo articleDraftVo) {
        articleDraftVo.setType(ArticleTypeEnum.DRAFT);
        if(articleDraftVo.getId()!=null){
            return articleRepository.updateArticleDraft(articleDraftVo);
        }else{
            return articleRepository.addArticleDraft(articleDraftVo);
        }
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
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticle(ArticleUpdateEntity articleUpdateEntity) {
        ArticleHandleVo articleHandleVo = ObjectConvertUtils.convert(articleUpdateEntity, ArticleHandleVo.class);
        ArticleUpdateEntity.CategoryVo articleVoCategory = articleUpdateEntity.getCategory();
        // 处理分类
        // 检查是否存在这个分类，存在则直接设置分类，不存在则插入后设置分类
        if (StringUtils.hasText(articleVoCategory.getCategoryKey())) {
            // 检查是否存在
            CategoryEntity category = categoryService.getCategory(articleVoCategory.getCategoryKey());
            if (category != null) {
                articleHandleVo.setCategoryKey(articleVoCategory.getCategoryKey());
            }
        } else {
            // 检查要插入的分类是否已经存在
            CategoryEntity categoryEntity = categoryService.getCategoryByName(articleVoCategory.getName());
            if (categoryEntity != null) {
                articleHandleVo.setCategoryKey(categoryEntity.getCategoryKey());
            } else {
                CategoryVo categoryVo = new CategoryVo();
                categoryVo.setName(articleVoCategory.getName());
                categoryEntity = categoryService.addArticleCategory(categoryVo);
                articleHandleVo.setCategoryKey(categoryEntity.getCategoryKey());
            }
        }
        // 处理标签
        // 获取旧的标签信息
        List<TagSimpleInfoEntity> oldTagList = tagService.getTagList(articleUpdateEntity.getArticleKey());
        // 记录当前新的标签
        ArrayList<TagEntity> newTagList = new ArrayList<>();
        List<ArticleUpdateEntity.TagVo> updateEntityTags = articleUpdateEntity.getTags();
        updateEntityTags.forEach(articleVoTag -> {
            String tagKey = articleVoTag.getTagKey();
            TagEntity tagEntity = null;
            // 检查是否存在标签
            if (StringUtils.hasText(tagKey)) {
                tagEntity = tagService.getTag(tagKey);
            } else {
                tagEntity = tagService.getTagByName(articleVoTag.getName());
                if (tagEntity == null) {
                    TagVo tagVo = new TagVo();
                    tagVo.setName(articleVoTag.getName());
                    tagEntity = tagService.addArticleTag(tagVo);
                }
            }
            // 给文章添加标签信息
            if (tagEntity != null) {
                ArticleAndTagLinkEntity articleAndTagLinkEntity = new ArticleAndTagLinkEntity();
                articleAndTagLinkEntity.setArticleKey(articleHandleVo.getArticleKey());
                articleAndTagLinkEntity.setTagKey(tagEntity.getTagKey());
                newTagList.add(tagEntity);
            }
        });
        // 找出需要新增的标签关联
        ArrayList<String> waitLinkList = new ArrayList<>();
        for (TagEntity tagEntity : newTagList) {
            for (TagSimpleInfoEntity tagSimpleInfoEntity : oldTagList) {
                if (tagEntity.getTagKey().equals(tagSimpleInfoEntity.getTagKey())) {
                    break;
                }
                waitLinkList.add(tagEntity.getTagKey());
            }
        }
        // 找出旧的标签里有的，新的标签里没有的，删除标签关联
        for (TagSimpleInfoEntity tagSimpleInfoEntity : oldTagList) {
            for (TagEntity tagEntity : newTagList) {
                if (tagEntity.getTagKey().equals(tagSimpleInfoEntity.getTagKey())) {
                    break;
                }
                tagService.deleteArticleAndTagLink(articleHandleVo.getArticleKey(), tagSimpleInfoEntity.getTagKey());
            }

        }
        // 去除掉重复标签后，开始添加新的标签关联
        waitLinkList.forEach(tagKey -> {
            ArticleAndTagLinkEntity articleAndTagLinkEntity = new ArticleAndTagLinkEntity();
            articleAndTagLinkEntity.setArticleKey(articleHandleVo.getArticleKey());
            articleAndTagLinkEntity.setTagKey(tagKey);
            tagService.linkArticleAndTag(articleAndTagLinkEntity);
        });
        // 更新文章信息
        return articleRepository.updateArticle(articleHandleVo);
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
