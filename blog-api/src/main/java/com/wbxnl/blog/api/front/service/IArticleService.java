package com.wbxnl.blog.api.front.service;

import com.wbxnl.blog.api.front.model.res.ArticleInfoRes;
import com.wbxnl.blog.api.front.model.res.ArticleSimpleRes;
import com.wbxnl.blog.api.front.model.res.NumberStatisticsRes;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.common.vo.Result;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 11:20
 */
public interface IArticleService {

    /**
     * 获取统计信息
     * @return 统计信息
     */
    NumberStatisticsRes getNumberStatistics();

    /**
     * 获取文章列表
     * @param pageParams 分页参数
     * @return 文章列表
     */
    PageData<ArticleSimpleRes> getPageOfArticles(PageParams pageParams);

    /**
     * 获取文章详情
     * @param id 文章id
     * @return 文章详情信息
     */
    ArticleInfoRes getArticleInfo(Integer id);

    /**
     * 获取文章归档
     * @param pageParams 分页参数
     * @return 文章归档
     */
    PageData<ArticleSimpleRes> getPageOfArchive(PageParams pageParams);
}
