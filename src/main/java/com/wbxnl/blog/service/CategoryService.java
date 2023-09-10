package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.CategoryDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.Category;
import com.wbxnl.blog.model.vo.CategoryVo;
import com.wbxnl.blog.model.vo.params.CategoryParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 文章类别 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface CategoryService extends BaseService<Category, CategoryDto, CategoryVo> {
    /**
     * 用户获取所有分类信息
     * @return
     */
    List<CategoryDto> getAllDetailByUser();

    /**
     * 用户获取分类数量
     * 用于主页显示文章概况时调用
     * @return
     */
    Long getCountByUser();

    /**
     * 查询分类对应的文章数量统计
     * @return
     */
    List<NameValueDto> getCategoryStatistics();
}
