package com.wbxnl.blog.domain.article.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.CategoryAggregate;
import com.wbxnl.blog.domain.article.model.entity.CategoryEntity;
import com.wbxnl.blog.domain.article.model.entity.CategoryQueryEntity;
import com.wbxnl.blog.domain.article.model.entity.CategorySimpleInfoEntity;
import com.wbxnl.blog.domain.article.model.entity.CategoryUpdateEntity;
import com.wbxnl.blog.domain.article.model.vo.CategoryHandleVo;
import com.wbxnl.blog.domain.article.model.vo.CategoryVo;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:21
 */
public interface ICategoryRepository {

    CategoryEntity addCategory(CategoryHandleVo categoryHandleVo);

    boolean updateCategory(CategoryUpdateEntity categoryUpdateEntity);

    boolean updateCategoryStatus(Integer id, String status);

    boolean deleteCategory(Integer id);

    boolean deleteCategory(Integer[] ids);

    List<CategorySimpleInfoEntity> getAllCategories();

    List<CategoryAggregate> getAllCategoryDetailsByUser();

    CategoryEntity getCategory(Integer id);

    CategoryEntity getCategory(String categoryKey);

    Long getCategoryQuantityByUser();

    PageData<CategoryEntity> getPageCategories(PageParams pageParams, CategoryQueryEntity categoryQueryEntity);

    CategoryEntity getCategoryByName(String name);
}
