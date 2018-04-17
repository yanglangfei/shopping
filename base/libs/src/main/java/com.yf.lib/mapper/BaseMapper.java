package com.yf.lib.mapper;

import java.util.List;
import java.util.Map;

/**
 * @param <T>
 */
public interface BaseMapper<T> {

    /**
     * 查询所有记录
     * @return
     */
    @Deprecated
    List<T> findAll();

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<T> findList(Map<String, Object> param);

    /**
     * 统计
     * @param param
     * @return
     */
    Integer count(Map<String, Object> param);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T findOne(Long id);

    /**
     * 根据参数查询一个数据
     * @param param
     * @return
     */
    T findOneByParam(Map<String, Object> param);

    /**
     * 根据ID查询，并且锁定
     * @param id
     * @return
     */
    T findOneLock(Long id);

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    Integer save(T entity);

    /**
     * 保存多个
     * @param entities
     * @return
     */
    Integer saveAll(List<T> entities);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    Integer update(T entity);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    Integer delete(Long id);

    /**
     * 删除ID列表删除
     * @param ids
     * @return
     */
    Integer deleteAll(Long[] ids);

}
