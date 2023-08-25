package com.wbxnl.blog.controller;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.model.dto.TalkDto;
import com.wbxnl.blog.model.entity.Talk;
import com.wbxnl.blog.model.vo.TalkVo;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.TalkParams;
import com.wbxnl.blog.service.SystemConfigService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.TalkService;

import lombok.extern.slf4j.Slf4j;
//import io.swagger.documentation.annotations.ApiIgnore;


/**
 * <p>
 * 说说 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/talk")
@Tag(name = "TalkController",description = "说说日志模块")
public class TalkController extends AbstractController<TalkService, Talk, TalkDto, TalkVo> {

    @Autowired
    private TalkService talkService;


    //    @GetMapping("/visitor/count")
//    //import io.swagger("游客查询说说访问量")
//    public Result getArticlesVisitorCount(@RequestParam("id")Long id){
//        Long count = systemConfigService.getVisitorCountOfTalk(id);
//        return new Result().ok(count);
//    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "用户根据id查询说说信息")
    public Result<TalkDto> getTalkByUser(@PathVariable("id") Integer id) {
        TalkDto talkDto = talkService.getTalkByUser(id);
        return new Result().ok(talkDto);
    }

    @GetMapping("/user/page")
    @Operation(summary = "用户分页查询说说列表")
    public Result<PageData<TalkDto>> getPageByUser(@ParameterObject PageParams pageParams) {
        PageData<TalkDto> list = talkService.getPageByUser(pageParams, new TalkParams());
        return new Result().ok(list);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询说说列表")
    public Result<PageData<TalkDto>> getPage(@ParameterObject PageParams pageParams, @ParameterObject TalkParams talkParams) {
        PageData<TalkDto> list = talkService.getPage(pageParams, talkParams);
        return new Result().ok(list);
    }
}
