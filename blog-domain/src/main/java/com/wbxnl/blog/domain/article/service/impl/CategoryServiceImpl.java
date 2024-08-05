package com.wbxnl.blog.domain.article.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.utils.UuidUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.CategoryAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.CategorySimpleAggregate;
import com.wbxnl.blog.domain.article.model.entity.CategoryEntity;
import com.wbxnl.blog.domain.article.model.entity.CategoryQueryEntity;
import com.wbxnl.blog.domain.article.model.entity.CategorySimpleInfoEntity;
import com.wbxnl.blog.domain.article.model.entity.CategoryUpdateEntity;
import com.wbxnl.blog.domain.article.model.vo.CategoryHandleVo;
import com.wbxnl.blog.domain.article.model.vo.CategoryVo;
import com.wbxnl.blog.domain.article.repository.ICategoryRepository;
import com.wbxnl.blog.domain.article.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 21:18
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    @Override
    public CategoryEntity addArticleCategory(CategoryVo categoryVo) {
        // 检查要插入的分类是否已经存在
        CategoryEntity categoryByName = categoryRepository.getCategoryByName(categoryVo.getName());
        if(categoryByName!=null){
            throw new BlogException(OperationCodeEnum.CATEGORY_EXISTS);
        }
        CategoryHandleVo categoryHandleVo = ObjectConvertUtils.convert(categoryVo, CategoryHandleVo.class);
        categoryHandleVo.setCategoryKey(UuidUtils.uuid());
        return categoryRepository.addCategory(categoryHandleVo);
    }

    @Override
    public boolean updateArticleCategory(CategoryUpdateEntity categoryUpdateEntity) {
        return categoryRepository.updateCategory(categoryUpdateEntity);
    }

    @Override
    public boolean updateArticleCategoryStatus(Integer id, String status) {
        return categoryRepository.updateCategoryStatus(id, status);
    }

    @Override
    public boolean deleteArticleCategory(Integer id) {
        return categoryRepository.deleteCategory(id);
    }

    @Override
    public boolean deleteArticleCategory(Integer[] ids) {
        return categoryRepository.deleteCategory(ids);
    }

    @Override
    public CategoryEntity getCategory(Integer id) {
        return categoryRepository.getCategory(id);
    }

    @Override
    public CategorySimpleInfoEntity getCategory(String categoryKey) {
        return categoryRepository.getCategory(categoryKey);
    }

    @Override
    public CategorySimpleInfoEntity getCategoryByUser(String categoryKey) {
        return categoryRepository.getCategoryByUser(categoryKey);
    }

    @Override
    public List<CategorySimpleInfoEntity> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public PageData<CategoryAggregate> getPageCategories(PageParams pageParams, CategoryQueryEntity categoryQueryEntity) {
        return categoryRepository.getPageCategories(pageParams, categoryQueryEntity);
    }

    @Override
    public List<CategorySimpleAggregate> getAllCategoryDetailsByUser() {
        return categoryRepository.getAllCategoryDetailsByUser();
    }

    @Override
    public Long getCategoryQuantityByUser() {
        return categoryRepository.getCategoryQuantityByUser();
    }
}
