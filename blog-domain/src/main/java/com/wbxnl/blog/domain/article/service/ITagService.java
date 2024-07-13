package com.wbxnl.blog.domain.article.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.TagAggregate;
import com.wbxnl.blog.domain.article.model.entity.*;
import com.wbxnl.blog.domain.article.model.vo.TagVo;

import java.util.List;

/**
 * description: 描述该文件的功能或目的.
 *
 * @author xiaowansheng
 * @since 2024/7/14 1:52
 */
public interface ITagService {
    /**
     * 添加文章标签
     * @param tagVo 文章标签信息
     * @return 添加后的标签结果，失败返回null
     */

    TagEntity addArticleTag(TagVo tagVo);

    /**
     * 删除文章标签
     * @param id 标签ID
     * @return 是否删除成功
     */
    boolean deleteArticleTag(Integer id);

    /**
     * 批量删除文章标签
     * @param ids 标签ID
     * @return 是否删除成功
     */
    boolean deleteArticleTag(Integer[] ids);

    /**
     * 更新文章标签
     * @param tagUpdateEntity 文章标签信息
     * @return 是否更新成功
     */
    boolean updateArticleTag(TagUpdateEntity tagUpdateEntity);

    /**
     * 获取文章标签信息
     * @param id 标签ID
     * @return 标签信息
     */
    TagEntity getTag(Integer id);

    /**
     * 批量获取文章标签信息
     * @param ids 标签ID集合
     * @return 标签列表
     */
    List<TagSimpleInfoEntity> getTags(Integer[] ids);

    /**
     * 获取所有简要标签信息
     * @return 标签列表
     */
    List<TagSimpleInfoEntity> getAllTags();


    /**
     * 分页获取标签
     * @param pageParams 分页参数
     * @param tagQueryEntity 查询参数
     * @return 标签列表
     */
    PageData<TagAggregate> getPageTags(PageParams pageParams, TagQueryEntity tagQueryEntity);


    /**
     * 添加文章标签和文章的关联
     * @param articleAndTagLinkEntity 文章标签关联
     * @return 是否添加成功
     */
    boolean linkArticleAndTag(ArticleAndTagLinkEntity articleAndTagLinkEntity);

    /**
     * 批量添加文章标签和文章的关联
     * @param articleAndTagLinkEntities 文章标签关联
     * @return 是否添加成功
     */
    boolean linkArticleAndTag(List<ArticleAndTagLinkEntity> articleAndTagLinkEntities);

    /**
     * 删除文章标签和文章的关联
     * @param id 关联ID
     * @return 是否删除成功
     */
    boolean unlinkArticleAndTag(Integer id);

    /**
     * 批量删除文章标签和文章的关联
     * @param ids 关联ID
     * @return 是否删除成功
     */
    boolean unlinkArticleAndTag(Integer[] ids);

    /**
     * 用户获取所有简要标签信息
     * @return 标签列表
     */
    List<TagAggregate> getAllTagsByUser();


    /**
     * 用户查询标签数量
     * @return 文章数量
     */
    Long getTagQuantityByUser();
}