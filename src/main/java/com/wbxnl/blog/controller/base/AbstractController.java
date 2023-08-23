package com.wbxnl.blog.controller.base;

import com.wbxnl.blog.annotation.Api;
import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.types.OperationType;
import com.wbxnl.blog.service.base.BaseService;
import com.wbxnl.blog.validator.group.Add;
import com.wbxnl.blog.validator.group.Update;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import io.swagger.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;

/**
 * @author wansheng
 * @createDate 2022/8/26 0:47
 */
@Slf4j
@Api("基类控制器")
public abstract class AbstractController<S extends BaseService<E,T,V>,E,T, V> {
    @Autowired
    S service;

//    /**
//     * 去除公共的分页查询接口
//     * 因为多数的分页查询操作都是需要进行条件查询和多表连接
//     *
//     * @return
//     */
//    @GetMapping("/page")
//    @Operation(summary = "分页查询数据")
//    public Result page(@ParameterObject @Validated PageParams pageParams,@Parameter(hidden = true) @RequestParam Map<String, Object> params){
//        PageData<T> page = service.page(pageParams,params);
//        return new Result<PageData<T>>().ok(page);
//    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id查询数据")
    public Result get(@PathVariable("id")Integer id){
        T dto = service.getDto(id);
        return new Result<T>().ok(dto);
    }

    @PostMapping("")
    @Log(type = OperationType.ADD,desc ="添加数据")
    @Operation(summary = "保存数据")
    public Result save(@Validated(Add.class) @RequestBody V vo) throws IllegalAccessException {
        service.saveVo(vo);
        return new Result().ok("保存成功！");
    }

    @PostMapping("/batch")
    @Log(type = OperationType.ADD_PATCH,desc ="批量添加数据")
    @Operation(summary = "批量保存数据")
    public Result save(@Validated(Add.class) @RequestBody List<V> vos){
        service.saveVoBatch(vos);
        return new Result().ok("批量保存成功！");
    }
    @PutMapping("")
    @Log(type = OperationType.UPDATE,desc ="修改数据")
    @Operation(summary = "修改数据")
    public Result update(@Validated(Update.class) @RequestBody V vo) throws IllegalAccessException {
        service.update(vo);
        return new Result().ok("修改成功！");
    }

    @PutMapping("/batch")
    @Log(type = OperationType.UPDATE_PATCH,desc ="批量修改数据")
    @Operation(summary = "批量修改数据")
    public Result update(@Validated(Update.class) @RequestBody List<V> vos){
        service.updateBatch(vos);
        return new Result().ok("修改成功！");
    }
    @DeleteMapping("/{id}")
    @Log(type = OperationType.DELETE,desc ="删除数据")
    @Operation(summary = "根据id删除对应数据")
    //@ApiImplicitParam(name = "id", value = "对象id", paramType = "delete", required = true, dataType="int")
    public Result delete(@PathVariable("id") Integer id){
        service.deleteById(id);
        return new Result().ok("删除成功！");
    }

    @DeleteMapping("/batch")
    @Log(type = OperationType.DELETE_PATCH,desc ="批量删除数据")
    @Operation(summary = "根据id批量删除对应数据")
    //@ApiImplicitParam(name ="ids", value = "对象的id数组", paramType = "delete", required = true, dataType="int")
    public Result delete(@RequestBody List<Integer> ids){
        log.info("删除的id集合："+Arrays.toString(ids.toArray()));
        service.deleteBatchByIds(ids);
        return new Result().ok("批量删除成功！");
    }
}
