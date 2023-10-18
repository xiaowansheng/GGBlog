package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.enums.ArticleTypeEums;
import com.wbxnl.blog.enums.ContentStateEums;
import com.wbxnl.blog.model.dto.CategoryDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.Category;
import com.wbxnl.blog.dao.CategoryDao;
import com.wbxnl.blog.model.vo.CategoryVo;
import com.wbxnl.blog.model.vo.params.ArticleParams;
import com.wbxnl.blog.model.vo.params.CategoryParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.service.CategoryService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章类别 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class CategoryServiceImpl extends AbstractServiceImpl<CategoryDao, Category, CategoryDto, CategoryVo> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<CategoryDto> getAllDetailByUser() {
        ArticleParams articleParams = new ArticleParams();
        articleParams.setType(ArticleTypeEums.DRAFT.getName());
        articleParams.setStatus(ContentStateEums.PUBLIC.getName());
        return categoryDao.getAllDetailByUser(articleParams);
    }

    @Override
    public Long getCountByUser() {
        return lambdaQuery().eq(Category::getHidden,0).count();
    }

    @Override
    public List<NameValueDto> getCategoryStatistics() {
        return categoryDao.getCategoryStatistics();
    }

    @Override
    public PageData<CategoryDto> getPage(PageParams pageParams, QueryParams queryParams) {
        CategoryParams categoryParams = (CategoryParams) queryParams;
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        List<CategoryDto> details = categoryDao.getDetails((page - 1) * limit, limit, categoryParams);
        Long count = count();
        return new PageData<CategoryDto>(details,count);
    }
}
