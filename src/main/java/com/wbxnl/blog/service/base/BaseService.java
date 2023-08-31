package com.wbxnl.blog.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @author wansheng
 * @createDate 2022/8/26 0:41
 */
public interface BaseService<E,T,V> extends IService<E>{
    /**
     * 保存数据
     *
     * @param vo
     * @return
     */
    public E saveVo(V vo);

    /**
     * 批量保存数据
     * @param vos
     */
    public void saveVoBatch(List<V> vos);

    /**
     * 根据id删除数据
     * @param id
     */
    public void deleteById(Integer id);

    /**
     * 批量删除数据
     * @param ids
     */
    public void deleteBatchByIds(List<Integer> ids);

    /**
     * 修改数据
     * @param vo
     */
    public void update(V vo);

    /**
     * 批量修改数据
     * @param vos
     */
    public void updateBatch(List<V> vos);

    /**
     * 根据id获取数据
     * @param id
     * @return
     */
    public T getDto(Integer id);


    /**
     * 用户分页查询
     * @Param pageParams
     * @param queryParams
     * @return
     */
    public PageData<T> getPageByUser(@NotNull(message = "分页参数对象不能为空") PageParams pageParams, @NotNull(message = "查询参数对象不能为空") QueryParams queryParams);
    /**
     * 分页查询
     * @Param pageParams
     * @param queryParams
     * @return
     */
    public PageData<T> getPage(@NotNull(message = "分页参数对象不能为空") PageParams pageParams,@NotNull(message = "查询参数对象不能为空") QueryParams queryParams);
}
