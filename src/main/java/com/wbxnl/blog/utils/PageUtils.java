package com.wbxnl.blog.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbxnl.blog.common.PageData;

import java.util.List;

/**
 * @author wansheng
 * @createDate 2022/8/26 5:31
 */
public class PageUtils {
    /**
     * 返回自己的数据对象
     * @return
     */

    public static  <T> PageData<T> getPageData(List<?> list, long total, Class<T> target){
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);

        return new PageData<T>(targetList, total);
    }

    public static  <T> PageData<T> getPageData(IPage page, Class<T> target){
        return getPageData(page.getRecords(), page.getTotal(), target);
    }
}
