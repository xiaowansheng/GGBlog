package com.wbxnl.blog.controller;

import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.controller.base.AbstractController;
import com.wbxnl.blog.enums.TopicTypeEums;
import com.wbxnl.blog.enums.UserTypeEums;
import com.wbxnl.blog.model.dto.CommentDto;
import com.wbxnl.blog.model.dto.extra.NameLabelDto;
import com.wbxnl.blog.model.entity.Comment;
import com.wbxnl.blog.model.vo.CommentVo;
import com.wbxnl.blog.model.vo.params.CommentParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.validator.custom.TopicType;
import com.wbxnl.blog.validator.group.Add;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wbxnl.blog.service.CommentService;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * 博客评论 前端控制器
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/comment")
@Tag(name = "CommentController", description = "评论内容模块")
public class CommentController extends AbstractController<CommentService, Comment, CommentDto, CommentVo> {

    @Autowired
    private CommentService commentService;

    @GetMapping("/review/page")
    @Operation(summary = "获取需要审核的评论列表")
    public Result getReview(@ParameterObject PageParams pageParams, @ParameterObject CommentParams commentParams) {
        PageData<CommentDto> review = commentService.getReviews(pageParams, commentParams);
        return new Result().ok(review);
    }

    @GetMapping("/user/type")
    @Operation(summary = "获取用户评论类型列表")
    public Result getUserCommentType() {
        List<NameLabelDto> list = Arrays.stream(UserTypeEums.values())
                .map(topicTypeEums -> new NameLabelDto(topicTypeEums.getName(), topicTypeEums.getLabel()))
                .collect(Collectors.toList());
        return new Result().ok(list);
    }

    @GetMapping("/topic/type")
    @Operation(summary = "获取话题类型列表")
    public Result getTopic() {
        List<NameLabelDto> list = Arrays.stream(TopicTypeEums.values())
                .map(topicTypeEums -> new NameLabelDto(topicTypeEums.getName(), topicTypeEums.getLabel()))
                .collect(Collectors.toList());
        return new Result().ok(list);
    }

    @GetMapping("/info/count")
    @Operation(summary = "用户获取评论数")
    public Result getCountByUser(@Parameter(description = "话题类型") @NotBlank(message = "话题类型不能为空") @TopicType @RequestParam(value = "topicType", required = true) String topicType,
                                 @Parameter(description = "话题编号") @RequestParam(value = "topicId", required = false) Integer topicId) {
        Long count = commentService.getCountByUser(topicType, topicId);
        return new Result().ok(count);
    }

    @GetMapping("/info/page")
    @Operation(summary = "用户分页获取评论列表", description = "获取的评论列表树形结构")
    public Result getPageByUser(@ParameterObject PageParams params,
                                @Parameter(description = "话题类型") @NotBlank(message = "话题类型不能为空") @TopicType @RequestParam(value = "topicType", required = true) String topicType,
                                @Parameter(description = "话题编号") @RequestParam(value = "topicId", required = false) Integer topicId) {
        CommentParams commentParams = new CommentParams();
        commentParams.setTopicType(topicType);
        commentParams.setTopicId(topicId);
        PageData<CommentDto> pageData = commentService.getPageByUser(params, commentParams);
        return new Result().ok(pageData);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询评论信息")
    public Result getPage(@ParameterObject PageParams params, @ParameterObject CommentParams commentParams) {
        PageData<CommentDto> pageData = commentService.getPage(params, commentParams);
        return new Result().ok(pageData);
    }


    @Override
    @PostMapping("")
    @Operation(summary = "用户添加评论信息")
    @Log(type = OperationType.ADD, desc = "添加数据")
    public Result save(@Validated(Add.class) @RequestBody CommentVo vo) {
        CommentDto commentDto = commentService.saveVoByUser(vo);
        return new Result().ok(commentDto);
    }

}
