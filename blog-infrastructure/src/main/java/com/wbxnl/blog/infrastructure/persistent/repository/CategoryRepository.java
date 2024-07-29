package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.enums.ArticleStatusEnum;
import com.wbxnl.blog.common.enums.ArticleTypeEnum;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.article.model.aggregate.CategoryAggregate;
import com.wbxnl.blog.domain.article.model.aggregate.CategorySimpleAggregate;
import com.wbxnl.blog.domain.article.model.entity.CategoryEntity;
import com.wbxnl.blog.domain.article.model.entity.CategoryQueryEntity;
import com.wbxnl.blog.domain.article.model.entity.CategorySimpleInfoEntity;
import com.wbxnl.blog.domain.article.model.entity.CategoryUpdateEntity;
import com.wbxnl.blog.domain.article.model.vo.CategoryHandleVo;
import com.wbxnl.blog.domain.article.repository.ICategoryRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.ArticleDao;
import com.wbxnl.blog.infrastructure.persistent.dao.CategoryDao;
import com.wbxnl.blog.infrastructure.persistent.po.Article;
import com.wbxnl.blog.infrastructure.persistent.po.Category;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/29 14:59
 */
@Service
@RequiredArgsConstructor
public class CategoryRepository implements ICategoryRepository {

    private final CategoryDao categoryDao;

    private final ArticleDao articleDao;

    @Override
    public CategoryEntity addCategory(CategoryHandleVo categoryHandleVo) {
        Category category = ObjectConvertUtils.convert(categoryHandleVo, Category.class);
        categoryDao.insert(category);
        return ObjectConvertUtils.convert(category, CategoryEntity.class);
    }

    @Override
    public boolean updateCategory(CategoryUpdateEntity categoryUpdateEntity) {
        Category category = ObjectConvertUtils.convert(categoryUpdateEntity, Category.class);
        return categoryDao.updateById(category) > 0;
    }

    @Override
    public boolean updateCategoryStatus(Integer id, String status) {
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<Category>()
                .eq(Category::getId, id)
                .set(Category::getHidden, status);
        return categoryDao.update(null, updateWrapper) > 0;
    }

    @Override
    public boolean deleteCategory(Integer id) {
        return categoryDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteCategory(Integer[] ids) {
        return categoryDao.deleteBatchIds(List.of(ids)) > 0;
    }

    @Override
    public List<CategorySimpleInfoEntity> getAllCategories() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId, Category::getCategoryKey, Category::getName, Category::getDescription);
        List<Category> categories = categoryDao.selectList(queryWrapper);
        return ObjectConvertUtils.convertList(categories, CategorySimpleInfoEntity.class);
    }

    @Override
    public List<CategorySimpleAggregate> getAllCategoryDetailsByUser() {
        // 查询所有的分类
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(Category::getId, Category::getCategoryKey, Category::getName, Category::getDescription);
        List<Category> categories = categoryDao.selectList(queryWrapper);
        List<CategorySimpleAggregate> simpleAggregates = ObjectConvertUtils.convertList(categories, CategorySimpleAggregate.class);
        // 查询文章分类数量统计
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.select("category_key as categoryKey, count(1) as articleCount")
                .ne("status",ArticleStatusEnum.PRIVATE.getStatus())
                .ne("type",ArticleTypeEnum.DRAFT.getType())
                .groupBy("category_key");
        // 转换文章分类数量，分类对应数量的map
        HashMap<String, Integer> articleCountMap = new HashMap<>();
        articleDao.selectMaps(articleQueryWrapper).forEach((value) -> {
            articleCountMap.put((String) value.get("categoryKey"), Integer.parseInt(value.get("articleCount").toString()));
        });
        // 设置文章分类数量
        simpleAggregates.forEach(simpleAggregate -> simpleAggregate.setArticleCount(articleCountMap.get(simpleAggregate.getCategoryKey())));
        return simpleAggregates;
    }

    @Override
    public CategoryEntity getCategory(Integer id) {
        Category category = categoryDao.selectById(id);
        return ObjectConvertUtils.convert(category, CategoryEntity.class);
    }

    @Override
    public CategoryEntity getCategory(String categoryKey) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>().eq(Category::getCategoryKey, categoryKey);
        return ObjectConvertUtils.convert(categoryDao.selectOne(queryWrapper), CategoryEntity.class);
    }

    @Override
    public Long getCategoryQuantityByUser() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getHidden, ArticleStatusEnum.OPEN.getStatus());
        return categoryDao.selectCount(queryWrapper);
    }

    @Override
    public PageData<CategoryAggregate> getPageCategories(PageParams pageParams, CategoryQueryEntity categoryQueryEntity) {
        // 分页查询文章分类
        Page<Category> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        Page<Category> selectedPage = categoryDao.selectPage(page, null);
        List<CategoryAggregate> categoryAggregates = ObjectConvertUtils.convertList(selectedPage.getRecords(), CategoryAggregate.class);
        // 查询文章分类数量统计
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper
                .select("category_key as categoryKey,id as ids")
                .groupBy("category_key","id");
        // 转换文章分类数量，分类对应数量的map
        HashMap<String, List<Integer>> articleCountMap = new HashMap<>();
        articleDao.selectMaps(articleQueryWrapper).forEach((value) -> {
            articleCountMap.put((String) value.get("categoryKey"), (List) value.get("ids"));
        });
        // 设置文章分类数量
        categoryAggregates.forEach(categoryAggregate -> {
            List<Integer> ids = articleCountMap.get(categoryAggregate.getCategoryKey());
            categoryAggregate.setArticleCount(ids.size());
            categoryAggregate.setArticleIds(ids);
        });
        return PageUtils.convertPageData(pageParams.getNumber(), pageParams.getSize(), selectedPage.getTotal(), categoryAggregates);
    }

    @Override
    public CategoryEntity getCategoryByName(String name) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>().eq(Category::getName, name);
        return ObjectConvertUtils.convert(categoryDao.selectOne(queryWrapper), CategoryEntity.class);
    }
}
