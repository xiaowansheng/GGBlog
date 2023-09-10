package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.enums.ArticleTypeEums;
import com.wbxnl.blog.enums.TopicTypeEums;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.model.dto.ArticleDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.dto.extra.StatisticsOfNumberDto;
import com.wbxnl.blog.model.entity.Article;
import com.wbxnl.blog.dao.ArticleDao;
import com.wbxnl.blog.model.entity.ArticleTag;
import com.wbxnl.blog.model.entity.Category;
import com.wbxnl.blog.model.vo.ArticleTagVo;
import com.wbxnl.blog.model.vo.ArticleVo;
import com.wbxnl.blog.model.vo.CategoryVo;
import com.wbxnl.blog.model.vo.TagVo;
import com.wbxnl.blog.model.vo.params.ArticleParams;
import com.wbxnl.blog.model.vo.params.DateIntervalParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.service.*;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.ConvertUtils;
import com.wbxnl.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客文章 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class ArticleServiceImpl extends AbstractServiceImpl<ArticleDao, Article, ArticleDto, ArticleVo> implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private PageViewService pageViewService;
    @Autowired
    private TalkService talkService;
    @Autowired
    private CommentService commentService;

    @Override
    public PageData<ArticleDto> getPageByUser(PageParams pageParams, QueryParams queryParams) {
        ArticleParams articleParams = (ArticleParams) queryParams;
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        List<ArticleDto> articleDto = articleDao.getPageByUser((page - 1) * limit, limit, articleParams);
        Long count = getCountByUser(articleParams);
        return new PageData<>(articleDto, count);
    }

    @Override
    public ArticleDto getArticleDtoByUser(Integer id) {
        // 查询文章数据
        ArticleDto articleDto = articleDao.getArticleDtoByUser(id);
        // 文章访问量增加
        if (ObjectUtils.isNotNull(articleDto)) {
            Long pageView = pageViewService.increasePageView(ViewTypeEums.ARTICLE.getName(), id);
            // 返回的数据中添加访问量信息
            articleDto.setPageView(pageView);
            //  添加评论数
            Long count = commentService.getCountByUser(TopicTypeEums.ARTICLE.getName(), id);
            articleDto.setCommentCount(count);
        }
        return articleDto;
    }

    @Override
    public Long getCountByUser(ArticleParams articleParams) {
        return articleDao.getCountByUser(articleParams);
    }

    @Override
    public PageData<ArticleDto> getArchiveByUser(PageParams pageParams) {
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        List<ArticleDto> archive = articleDao.getArchiveByUser((page - 1) * limit, limit);
        Long count = getCountByUser(new ArticleParams());
        return new PageData<>(archive, count);
    }


    @Override
    public PageData<ArticleDto> getPage(PageParams pageParams, QueryParams queryParams) {
        ArticleParams articleParams = (ArticleParams) queryParams;
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        List<ArticleDto> list = articleDao.getPage((page - 1) * limit, limit, articleParams);
        Long count = articleDao.getCount(articleParams);
        return new PageData<>(list, count);
    }

    @Override
    public PageData<ArticleDto> getArchive(PageParams pageParams) {
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        List<ArticleDto> archive = articleDao.getArchive((page - 1) * limit, limit);
        long count = count();
        return new PageData<>(archive, count);
    }

    @Override
    public StatisticsOfNumberDto getStatisticsOfCountByUser() {
        Long articleCount = getCountByUser(new ArticleParams());
        Long categoryCount = categoryService.getCountByUser();
        Long tagCount = tagService.getCountByUser();
        Long talkCount = talkService.getCountByUser();
        return new StatisticsOfNumberDto(articleCount, categoryCount, tagCount, talkCount);
    }

    @Override
    public StatisticsOfNumberDto getStatisticsOfCount() {
        long articleCount = count();
        long categoryCount = categoryService.count();
        long tagCount = tagService.count();
        long talkCount = talkService.count();
        return new StatisticsOfNumberDto(articleCount, categoryCount, tagCount, talkCount);
    }

    @Override
    public ArticleDto saveDraft(ArticleVo articleVo) {
        Article article = ConvertUtils.sourceToTarget(articleVo, Article.class);
        article.setUserAuthId(SecurityUtils.getUserInfoId());
        // 文章类型设置为草稿
        article.setType(ArticleTypeEums.DRAFT.getName());
        saveOrUpdate(article);
        return ConvertUtils.sourceToTarget(article, ArticleDto.class);
    }

    @Override
    public List<NameValueDto> getStatisticsOfCount(DateIntervalParams dateIntervalParams) {
        return articleDao.getStatisticsOfCount(dateIntervalParams);
    }

    @Override
    public ArticleDto getDto(Integer id) {
        return articleDao.getArticleDto((long) id);
    }

    /**
     * 文章发布
     *
     * @param articleVo
     * @return
     */
    @Override
    @Transactional
    public Article saveVo(ArticleVo articleVo) {
        Article article = ConvertUtils.sourceToTarget(articleVo, Article.class);
        article.setUserAuthId(SecurityUtils.getUserAuthId());
        CategoryVo categoryVo = articleVo.getCategoryVo();
        Category category = ConvertUtils.sourceToTarget(categoryVo, Category.class);
        List<TagVo> tagVos = articleVo.getTagVos();
        //设置文章分类
        // 如果是新增的分类，则先添加新的分类信息
        if (ObjectUtils.isNull(category.getId())) {
            category.setId(null);
            category.setHidden(0);
            categoryService.save(category);
        }
        article.setCategoryId(category.getId());
        //添加或修改文章
        // 文章编号是null，则是添加文章，不是则修改文章
        if (ObjectUtils.isNull(article.getId())) {
            save(article);
            articleVo.setId(article.getId());
        } else {
            updateById(article);
        }
        // 删除当前的文章的所有标签信息
        articleTagService.lambdaUpdate().eq(ArticleTag::getArticleId, article.getId()).remove();
        //设置文章新的标签
        // 过滤得到新的标签，需要将这些新标签添加到数据库中
        List<TagVo> newTags = tagVos.stream().filter(tag -> tag.getId() == null || tag.getId() == 0).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(newTags)) {
            tagService.saveVoBatch(newTags);
        }
        // 添加文章标签
        List<ArticleTagVo> articleTagList = tagVos.stream().map(tagVo -> ArticleTagVo.builder()
                .tagId(tagVo.getId())
                .articleId(article.getId())
                .build()).collect(Collectors.toList());
        articleTagService.saveVoBatch(articleTagList);

        return article;
    }

    @Override
    public void update(ArticleVo articleVo) {
        saveVo(articleVo);
    }

}
