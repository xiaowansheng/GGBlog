package com.wbxnl.blog.trigger.http.front;

import com.wbxnl.blog.api.front.model.res.ArticleInfoRes;
import com.wbxnl.blog.api.front.model.res.ArticleSimpleRes;
import com.wbxnl.blog.api.front.model.res.NumberStatisticsRes;
import com.wbxnl.blog.api.front.service.IArticleService;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:30
 */
@Slf4j
@RestController
@RequestMapping("/front/article")
@RequiredArgsConstructor
public class ArticleController implements IArticleService {

    private final com.wbxnl.blog.domain.article.service.IArticleService articleService;

    @Override
    public NumberStatisticsRes getNumberStatistics() {
        return null;
    }

    @Override
    public PageData<ArticleSimpleRes> getPageOfArticles(PageParams pageParams) {
        return null;
    }

    @Override
    public ArticleInfoRes getArticleInfo(Integer id) {
        return null;
    }

    @Override
    public PageData<ArticleSimpleRes> getPageOfArchive(PageParams pageParams) {
        return null;
    }
}
