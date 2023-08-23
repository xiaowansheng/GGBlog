package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Api;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.model.dto.PageViewDto;
import com.wbxnl.blog.model.dto.extra.NameLabelDto;
import com.wbxnl.blog.model.entity.PageView;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.PageViewParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.PageViewService;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 访问量 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/page/view")
@Tag(name = "PageViewController", description = "访问流量模块")
public class PageViewController extends AbstractController<PageViewService, PageView, PageViewDto, PageView> {

    @Autowired
    private PageViewService pageViewService;

    @GetMapping("/type/list")
    @Operation(summary = "获取所有的访问的视图类型")
    public Result getViewTypes(){
        List<NameLabelDto> list = Arrays.stream(ViewTypeEums.values())
                .map(viewTypeEums -> new NameLabelDto(viewTypeEums.getName(), viewTypeEums.getLabel()))
                .collect(Collectors.toList());
        return new Result().ok(list);
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取访问量数据")
    public Result getPage(@ParameterObject PageParams pageParams, @ParameterObject PageViewParams pageViewParams) {
        PageData<PageViewDto> pageData = pageViewService.getPage(pageParams, pageViewParams);
        return new Result().ok(pageData);
    }

}
