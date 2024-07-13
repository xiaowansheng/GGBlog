package com.wbxnl.blog.domain.article.service;

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
 * description: 描述该文件的功能或目的.
 *
 * @author xiaowansheng
 * @since 2024/7/14 1:51
 */
public interface ICategoryService {
    /**
     * 添加文章分类信息
     *
     * @param categoryVo 分类信息
     * @return 返回具体的分类信息，失败返回null
     */
    CategoryEntity addArticleCategory(CategoryVo categoryVo);


    /**
     * 修改文章分类信息
     *
     * @param categoryUpdateEntity 分类更新信息
     * @return 是否修改成功
     */
    boolean updateArticleCategory(CategoryUpdateEntity categoryUpdateEntity);

    /**
     * 删除文章分类信息
     *
     * @param id 分类ID
     * @return 是否删除成功
     */
    boolean deleteArticleCategory(Integer id);

    /**
     * 批量删除文章分类信息
     *
     * @param ids 分类ID集合
     * @return 是否删除成功
     */
    boolean deleteArticleCategory(Integer[] ids);

    /**
     * 获取文章分类信息
     *
     * @param id 分类ID
     * @return 分类信息
     */
    CategoryEntity getCategory(Integer id);

    /**
     * 获取所有简要的分类信息
     *
     * @return 分类信息
     */
    List<CategorySimpleInfoEntity> getAllCategories();

    /**
     * 获取分页的分类信息
     *
     * @param pageParams          分页参数
     * @param categoryQueryEntity 查询参数
     * @return 分类信息
     */
    PageData<CategoryEntity> getPageCategories(PageParams pageParams, CategoryQueryEntity categoryQueryEntity);


    /**
     * 用户查询分类信息
     * 只能获取开放的分类
     * @return 分类信息集合
     */
    List<CategoryAggregate> getAllCategoryDetailsByUser();


    /**
     * 用户查询分类数量
     *
     * @return 分类数量
     */
    Long getCategoryQuantityByUser();
}
