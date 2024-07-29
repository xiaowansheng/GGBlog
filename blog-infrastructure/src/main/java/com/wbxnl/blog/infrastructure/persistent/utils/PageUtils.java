package com.wbxnl.blog.infrastructure.persistent.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbxnl.blog.common.vo.PageData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/29 11:13
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class PageUtils {
    /**
     * 转换分页数据为 PageData
     * @param number 页号
     * @param size 每页大小
     * @param total 总数
     * @param data 数据
     * @return PageData
     * @param <T> 数据类型
     */
    public static <T> PageData<T> convertPageData(int number, int size, long total, List<T> data) {
        PageData<T> pageData = new PageData<>();
        pageData.setNumber(number);
        pageData.setSize(size);
        pageData.setTotal((int) total);
        pageData.setData(data);
        return pageData;
    }

    /**
     * 转换分页数据为 PageData
     * @param page 分页数据
     * @return PageData
     * @param <T> 数据类型
     */
    public static <T> PageData<T> convertPageData(IPage<T> page) {
        return convertPageData((int) page.getCurrent(), (int) page.getSize(), page.getTotal(), page.getRecords());
    }
}