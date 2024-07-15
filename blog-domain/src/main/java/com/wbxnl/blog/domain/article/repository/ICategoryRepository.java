package com.wbxnl.blog.domain.article.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.CategoryAggregate;
import com.wbxnl.blog.domain.article.model.entity.CategoryEntity;
import com.wbxnl.blog.domain.article.model.entity.CategoryQueryEntity;
import com.wbxnl.blog.domain.article.model.entity.CategorySimpleInfoEntity;
import com.wbxnl.blog.domain.article.model.entity.CategoryUpdateEntity;
import com.wbxnl.blog.domain.article.model.vo.CategoryVo;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:21
 */
public interface ICategoryRepository {

    CategoryEntity addCategory(CategoryVo categoryVo);

    boolean updateCategory(CategoryUpdateEntity categoryUpdateEntity);

    boolean updateCategoryStatus(Integer id, String status);

    boolean deleteCategory(Integer id);

    boolean deleteCategory(Integer[] ids);

    List<CategorySimpleInfoEntity> getAllCategories();

    List<CategoryAggregate> getAllCategoryDetailsByUser();

    CategoryEntity getCategory(Integer id);

    Long getCategoryQuantityByUser();

    PageData<CategoryEntity> getPageCategories(PageParams pageParams, CategoryQueryEntity categoryQueryEntity);
}
