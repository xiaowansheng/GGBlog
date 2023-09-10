package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.ArticleDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.Article;
import com.wbxnl.blog.dao.base.BaseDao;
import com.wbxnl.blog.model.vo.ArticleVo;
import com.wbxnl.blog.model.vo.params.ArticleParams;
import com.wbxnl.blog.model.vo.params.DateIntervalParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 博客文章 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface ArticleDao extends BaseDao<Article> {

    /**
     * 游客查询文章归档信息
     * @param current
     * @param limit
     * @return
     */
    public  List<ArticleDto> getArchiveByUser(@Param("current")Long current, @Param("limit")Long limit);

    /**
     * 游客分页查询文章列表
     * @param current
     * @param limit
     * @param articleParams
     * @return
     */
    public List<ArticleDto> getPageByUser(@Param("current")Long current, @Param("limit")Long limit, @Param("article") ArticleParams articleParams);


    /**
     * 用户根据条件查询文章数量
     * @param articleParams
     * @return
     */
    public Long getCountByUser(@Param("article") ArticleParams articleParams);

    /**
     * 游客查询文章详细信息
     * @param id
     * @return
     */
    public ArticleDto getArticleDtoByUser(@Param("id")Integer id);

    /**
     * 管理员查询文章归档信息
     * @param current
     * @param limit
     * @return
     */
    public List<ArticleDto> getArchive(@Param("current")Long current, @Param("limit")Long limit);

    /**
     * 管理员分页获取文章详细数据
     * @param articleParams
     * @return
     */
    public List<ArticleDto> getPage(@Param("current")Long current, @Param("limit")Long limit,@Param("article") ArticleParams articleParams);

    /**
     * 根据条件查询文章数量
     * @param articleParams
     * @return
     */
    public Long getCount(@Param("article") ArticleParams articleParams);
    /**
     * 管理员根据id查询文章详细信息
     * @param id
     * @return
     */
    public ArticleDto getArticleDto(@Param("id")Long id);

    /**
     * 根据日期查询文章统计
     * @param dateIntervalParams
     * @return
     */
    List<NameValueDto> getStatisticsOfCount(@Param("queryParams") DateIntervalParams dateIntervalParams);
}
