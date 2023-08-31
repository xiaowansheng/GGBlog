package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Api;
import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.model.dto.FriendDto;
import com.wbxnl.blog.model.entity.Friend;
import com.wbxnl.blog.model.entity.Role;
import com.wbxnl.blog.model.vo.FriendVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import com.wbxnl.blog.model.vo.extra.StatusVo;
import com.wbxnl.blog.model.vo.params.CommentParams;
import com.wbxnl.blog.model.vo.params.FriendParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.PageViewService;
import com.wbxnl.blog.validator.group.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.FriendService;

import lombok.extern.slf4j.Slf4j;
//import io.swagger.documentation.annotations.ApiIgnore;


/**
 * <p>
 * 友情链接 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@RequestMapping("/friend")
@Tag(name = "FriendController",description = "友情链接模块")
public class FriendController extends AbstractController<FriendService, Friend, FriendDto, FriendVo> {

    @Autowired
    private FriendService friendService;
    @Autowired
    private PageViewService pageViewService;

    @GetMapping("/user/page")
    @Operation(summary = "用户分页查询友链数据")
    public Result<PageData<FriendDto>> getPageByUser(@ParameterObject PageParams pageParams){
        PageData<FriendDto> pageData = friendService.getPageByUser(pageParams,new CommentParams());
        // 添加访客记录
        if(pageParams.getPage()==1){
            pageViewService.increasePageView(ViewTypeEums.FRIEND.getName(), null);
        }
        return new Result().ok(pageData);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询友链数据")
    public Result<PageData<FriendDto>> getPage(@ParameterObject PageParams pageParams,@ParameterObject FriendParams friendParams){
        PageData<FriendDto> pageData = friendService.getPage(pageParams,friendParams);
        return new Result().ok(pageData);
    }


    @PutMapping("/status")
    @Operation(summary = "改变友链的状态信息")
    @Log(type = OperationType.UPDATE,desc = "修改友链的隐藏状态信息")
    public Result updateStatus(@Validated({Update.class}) @RequestBody StatusVo statusVo){
        friendService.lambdaUpdate().eq(Friend::getId,statusVo.getId()).set(Friend::getHidden,statusVo.getStatus()).update();
        return new Result();
    }
}
