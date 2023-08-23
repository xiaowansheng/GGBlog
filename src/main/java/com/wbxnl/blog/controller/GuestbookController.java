package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Api;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.model.dto.GuestbookDto;
import com.wbxnl.blog.model.entity.Guestbook;
import com.wbxnl.blog.model.vo.GuestbookVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.model.vo.params.GuestbookParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.PageViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wbxnl.blog.service.GuestbookService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import io.swagger.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * <p>
 * 留言簿 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/guestbook")
@Tag(name = "GuestbookController",description = "留言板模块")
public class GuestbookController extends AbstractController<GuestbookService, Guestbook, GuestbookDto, GuestbookVo> {

    @Autowired
    private GuestbookService guestbookService;
    @Autowired
    private PageViewService pageViewService;


    @GetMapping("/user/page")
    @Operation(summary = "用户分页查询留言数据")
    public Result getPageByUser(@ParameterObject PageParams pageParams){
        PageData<GuestbookDto> page = guestbookService.getPageByUser(pageParams,new GuestbookParams());
        // 添加访客记录
        if(pageParams.getPage()==1){
            pageViewService.increasePageView(ViewTypeEums.GUESTBOOK.getName(), null);
        }
        return new Result().ok(page);
    }

    @GetMapping("/review/page")
    @Operation(summary = "获取需要审核的留言列表")
    public Result getReview(@ParameterObject PageParams pageParams){
        PageData<GuestbookDto> review = guestbookService.getReviews(pageParams);
        return new Result().ok(review);
    }
    @GetMapping("/page")
    @Operation(summary = "分页查询留言数据")
    public Result getPage(@ParameterObject PageParams pageParams, @ParameterObject GuestbookParams guestbookParams){
        PageData<GuestbookDto> page = guestbookService.getPageByUser(pageParams,guestbookParams);
        return new Result().ok(page);
    }
}
