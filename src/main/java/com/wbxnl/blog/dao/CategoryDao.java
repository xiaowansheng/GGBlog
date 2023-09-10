package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.CategoryDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.Category;
import com.wbxnl.blog.dao.base.BaseDao;
import com.wbxnl.blog.model.vo.params.CategoryParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章类别 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface CategoryDao extends BaseDao<Category> {
    /**
     * 分页获取分类详细数据
     * @param current
     * @param limit
     * @param categoryParams
     * @return
     */
    List<CategoryDto> getDetails(@Param("current") Long current, @Param("limit") Long limit, @Param("category")CategoryParams categoryParams);

    /**
     * 用户查询所有的分类详情信息
     * @return
     */
    List<CategoryDto> getAllDetailByUser();

    /**
     * 查询分类对应的文章数量统计
     * @return
     */
    List<NameValueDto> getCategoryStatistics();
}
