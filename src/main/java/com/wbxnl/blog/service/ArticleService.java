package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.ArticleDto;
import com.wbxnl.blog.model.dto.extra.StatisticsOfNumberDto;
import com.wbxnl.blog.model.entity.Article;
import com.wbxnl.blog.model.vo.ArticleVo;
import com.wbxnl.blog.model.vo.params.ArticleParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.base.BaseService;

/**
 * <p>
 * 博客文章 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface ArticleService extends BaseService< Article, ArticleDto, ArticleVo> {

    /**
     * 游客查询文章详细信息
     *  并且添加文章的用户浏览信息
     * @param id
     * @return
     */
    public ArticleDto getArticleDtoByUser(Integer id);

    /**
     * 用户查询文章数量
     * @param articleParams
     * @return
     */
    public Long getCountByUser(ArticleParams articleParams);

    /**
     * 游客查询文章归档信息
     * @param pageParams
     * @return
     */
    public  PageData<ArticleDto> getArchiveByUser(PageParams pageParams);

    /**
     * 管理员获取文章归档信息列表
     * @param pageParams
     * @return
     */
    PageData<ArticleDto> getArchive(PageParams pageParams);

    /**
     * 用户获取文章等统计的数据
     * @return
     */
    StatisticsOfNumberDto getStatisticsOfCountByUser();

    /**
     * 获取关于文章等相关统计的数据
     * @return
     */
    StatisticsOfNumberDto getStatisticsOfCount();

    /**
     * 保存文章草稿，并返回保存后的数据
     * @param articleVo
     * @return
     */
    ArticleDto saveDraft(ArticleVo articleVo);
}
