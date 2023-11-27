package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.CategoryDto;
import com.wbxnl.blog.model.dto.extra.IDNameDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.Article;
import com.wbxnl.blog.model.entity.Category;
import com.wbxnl.blog.model.vo.CategoryVo;
import com.wbxnl.blog.model.vo.extra.StatusVo;
import com.wbxnl.blog.model.vo.params.CategoryParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.utils.ConvertUtils;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章类别 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/category")
@Tag(name = "CategoryController", description = "文章分类模块")
public class CategoryController extends AbstractController<CategoryService, Category, CategoryDto, CategoryVo> {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/detail/{id}")
    @Operation(summary = "用户根据id查询分类信息")
    public Result<CategoryDto> getDetailByUser(@PathVariable("id")String id){
        Category category = categoryService.lambdaQuery().eq(Category::getHidden, 0).eq(Category::getId, id).one();
        if(category==null){
            category=new Category();
        }
        CategoryDto categoryDto = ConvertUtils.sourceToTarget(category, CategoryDto.class);
        return new Result().ok(categoryDto);
    }

    @PutMapping("/status")
    @Operation(summary = "改变分类状态信息")
    @Log(type = OperationType.UPDATE,desc = "改变分类状态信息")
    public Result updateStatus(@Validated({Update.class}) @RequestBody StatusVo statusVo){
        categoryService.lambdaUpdate().eq(Category::getId,statusVo.getId()).set(Category::getHidden,statusVo.getStatus()).update();
        return new Result();
    }

    @GetMapping("/statistics")
    @Operation(summary = "获取所有的博客分类的文章数统计")
    public Result<List<NameValueDto>> getCategoryStatistic() {
        List<NameValueDto> list = categoryService.getCategoryStatistics();
        return new Result().ok(list);
    }

    @GetMapping("/user/list")
    @Operation(summary = "用户获取所有的博客分类")
    public Result<List<CategoryDto>> getAllListByUser() {
        List<CategoryDto> list = categoryService.getAllDetailByUser();
        return new Result().ok(list);
    }

    @GetMapping("/detail/page")
    @Operation(summary = "分页获取分类的详细信息")
    public Result<PageData<CategoryDto>> getDetailPage(@ParameterObject PageParams pageParams, @ParameterObject CategoryParams categoryParams){
        PageData<CategoryDto> pageData =categoryService.getPage(pageParams,categoryParams);
        return new Result().ok(pageData);
    }


    @GetMapping("/simple/list")
    @Operation(summary = "获取所有的博客分类简略数据")
    public Result<List<IDNameDto>> getSimpleList() {
        List<Category> list = categoryService.lambdaQuery().select(Category::getId, Category::getName).list();
        List<IDNameDto> nameLabelDtos = list.stream().map(category -> new IDNameDto(category.getId(), category.getName())).collect(Collectors.toList());
        return new Result().ok(nameLabelDtos);
    }
}
