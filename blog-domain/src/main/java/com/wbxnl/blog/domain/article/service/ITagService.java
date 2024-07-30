package com.wbxnl.blog.domain.article.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.TagAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.TagSimpleAggregate;
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
     * 根据标签Key获取文章标签信息
     * @param tagKey 标签Key
     * @return 标签信息
     */
    TagEntity getTag(String tagKey);

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
     * @param articleKey 文章key
     * @return 是否删除成功
     */
    boolean deleteArticleAndTagLink(String articleKey);

    /**
     * 批量删除文章标签和文章的关联
     * @param articleKey 文章key
     * @param tagKey 标签key
     * @return 是否删除成功
     */
    boolean deleteArticleAndTagLink(String articleKey,String tagKey);

    /**
     * 根据文章key获取标签信息
     * @param articleKey 文章Key
     * @return 标签列表
     */
    List<TagSimpleInfoEntity> getTagList(String articleKey);

    /**
     * 用户获取所有简要标签信息
     * @return 标签列表
     */
    List<TagSimpleAggregate> getAllTagsByUser();


    /**
     * 用户查询标签数量
     * @return 文章数量
     */
    Long getTagQuantityByUser();

    /**
     * 根据标签名称获取标签信息
     * @param name 标签名称
     * @return 标签信息
     */
    TagEntity getTagByName(String name);
}