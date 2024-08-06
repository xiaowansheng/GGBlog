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
    public CategoryEntity addCategory(CategoryVo categoryVo) {
        // 检查要插入的分类是否已经存在
        CategoryEntity categoryByName = categoryRepository.getCategoryByName(categoryVo.getName());
        if(categoryByName!=null){
            throw new BlogException(OperationCodeEnum.CATEGORY_EXISTS);
        }
        CategoryHandleVo categoryHandleVo = ObjectConvertUtils.convert(categoryVo, CategoryHandleVo.class);
        categoryHandleVo.setCategoryKey(UuidUtils.uuid());
        CategoryEntity categoryEntity = categoryRepository.addCategory(categoryHandleVo);
        if(categoryEntity==null){
            throw new BlogException(OperationCodeEnum.ADD_FAILURE);
        }
        return categoryEntity;
    }

    @Override
    public void updateCategory(CategoryUpdateEntity categoryUpdateEntity) {
        boolean updated = categoryRepository.updateCategory(categoryUpdateEntity);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void updateCategoryStatus(Integer id, String status) {
        boolean updated = categoryRepository.updateCategoryStatus(id, status);
        if(!updated){
            throw new BlogException(OperationCodeEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        boolean updated = categoryRepository.deleteCategory(id);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
    }

    @Override
    public void deleteCategory(Integer[] ids) {
        boolean updated = categoryRepository.deleteCategory(ids);
        if(!updated){
            throw new BlogException(OperationCodeEnum.DELETE_FAILURE);
        }
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
    public PageData<CategoryAggregate> getPageOfCategories(PageParams pageParams, CategoryQueryEntity categoryQueryEntity) {
        return categoryRepository.getPageCategories(pageParams, categoryQueryEntity);
    }

    @Override
    public Long getCategoryQuantity() {
        return categoryRepository.getCategoryQuantity();
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
