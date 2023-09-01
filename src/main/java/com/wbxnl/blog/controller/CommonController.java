package com.wbxnl.blog.controller;

import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.enums.ContentStateEums;
import com.wbxnl.blog.model.dto.extra.NameLabelDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xiaowansheng
 * @Date 2023/9/1 14:21
 */
@RestController
@RequestMapping("/common")
@Tag(name = "",description = "通用模块")
public class CommonController {

    @GetMapping("/content/status")
    @Operation(summary = "获取所有的内容访问状态信息")
    public Result<List<NameLabelDto>> getContentStatus(){
        List<NameLabelDto> list = Arrays.stream(ContentStateEums.values())
                .map(c -> new NameLabelDto(c.getName(), c.getLabel()))
                .collect(Collectors.toList());
        return new Result().ok(list);
    }
}
