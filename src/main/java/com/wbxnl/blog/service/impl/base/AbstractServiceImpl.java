package com.wbxnl.blog.service.impl.base;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.dao.base.BaseDao;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.base.QueryParams;
import com.wbxnl.blog.service.base.BaseService;
import com.wbxnl.blog.utils.ConvertUtils;
import com.wbxnl.blog.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wansheng
 * @createDate 2022/8/26 0:42
 */
@Slf4j
public abstract class AbstractServiceImpl<D extends BaseDao<E>,E,T,V> extends ServiceImpl<BaseDao<E>, E> implements BaseService<E,T,V> {


    protected Class<E> currentEntityClass() {
//        System.out.println("entity:"+(Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
        return (Class<E>) ReflectionKit.getSuperClassGenericType(getClass(), AbstractServiceImpl.class, 1);
    }
    protected Class<T> currentDtoClass() {
//        System.out.println("dto:"+(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2]);
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), AbstractServiceImpl.class, 2);
    }

    protected Class<V> currentVoClass() {
//        System.out.println("vo:"+(Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3]);
        return (Class<V>) ReflectionKit.getSuperClassGenericType(getClass(), AbstractServiceImpl.class, 3);
    }

//    /**
//     * 解决mybatis-plus填充时间失效问题
//     * @param entity
//     * @throws IllegalAccessException
//     */
//    protected  void setInsertDate(E entity) throws IllegalAccessException {
//        log.info("手动插入数据时给属性赋值。。。");
//        Field[] fields = entity.getClass().getDeclaredFields();
//        for (int i=0;i<fields.length;i++){
//            fields[i].setAccessible(true);
//            if(fields[i].getName().equals("createTime")||fields[i].getName().equals("updateTime")){
//                fields[i].set(entity,new Date());
//            }
//        }
//    }
//    /**
//     * 解决mybatis-plus填充时间失效问题
//     * @param entity
//     * @throws IllegalAccessException
//     */
//    protected  void setUpdateDate(E entity) throws IllegalAccessException {
//        log.info("手动修改数据时给属性赋值。。。");
//        Field[] fields = entity.getClass().getDeclaredFields();
//        for (int i=0;i<fields.length;i++){
//            fields[i].setAccessible(true);
//            if(fields[i].getName().equals("updateTime")){
//                fields[i].set(entity,new Date());
//                return;
//            }
//        }
//    }
    @Override
    public E saveVo(V vo) {
        E entity = ConvertUtils.sourceToTarget(vo, currentEntityClass());
//        setInsertDate(entity);
//        log.info("添加的数据："+entity.toString());
        save(entity);
        return entity;
    }

    @Override
    public void saveVoBatch(List<V> vos) {
        if(CollectionUtils.isEmpty(vos)){
            return;
        }

        List<E> entities = ConvertUtils.sourceToTarget(vos, currentEntityClass());
        // TODO 批量插入出现 org.mybatis.spring.MyBatisSystemException 异常

//        saveBatch(entities,entities.size());
        entities.forEach(e->{
            save(e);
        });
    }

    @Override
    public void deleteById(Integer id) {
        removeById(id);
    }

    @Override
    public void deleteBatchByIds(List<Integer> ids) {
        removeBatchByIds(ids,ids.size());
    }

    @Override
    public void update(V vo) {
        E entity = ConvertUtils.sourceToTarget(vo, currentEntityClass());
        updateById(entity);
    }

    @Override
    public void updateBatch(List<V> vos) {
        List<E> entities = ConvertUtils.sourceToTarget(vos, currentEntityClass());
        updateBatchById(entities);
    }

    @Override
    public T getDto(Integer id){
        E entity = getById(id);
        return ConvertUtils.sourceToTarget(entity, currentDtoClass());
    }

    @Override
    public PageData<T> getPageByUser(PageParams pageParams,QueryParams queryParams) {
        // 设置查询参数参数
        setQueryParamsByUser(queryParams);
        // 分页查询
        return getPage(pageParams,queryParams);
    }

    /**
     * 设置用户查询时的参数
     * 【限制用户对于某些内容的访问时，使用该方法进行隐私参数设置，如数据是否公开，是否隐藏等】
     * @param queryParams
     */
    public void setQueryParamsByUser(QueryParams queryParams){};

    @Override
    public PageData<T> getPage(PageParams pageParams,QueryParams queryParams) {
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        Page<E> ePage = new Page<>(page, limit);
        LambdaQueryWrapper<E> wrapper = getQueryWrapper(queryParams);
        return getPageData(ePage, wrapper);
    }

    /**
     * 分页查询时，把查询参数放到查询条件中
     * 【自定义封装时，重写该方法】
     * @param queryParams
     * @return
     */
    public LambdaQueryWrapper<E> getQueryWrapper(QueryParams queryParams){
        return new LambdaQueryWrapper<>();
    }

    /**
     * 根据分页参数和查询参数，获取分页数据
     * @param page
     * @param lambdaWrapper
     * @return
     */
    public PageData<T> getPageData(Page<E> page, AbstractLambdaWrapper lambdaWrapper){
        Page selectPage = baseMapper.selectPage(page, lambdaWrapper);
        return PageUtils.getPageData(selectPage,currentDtoClass());
    }
}
