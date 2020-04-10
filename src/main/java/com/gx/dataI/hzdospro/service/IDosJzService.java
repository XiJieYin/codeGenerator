package com.gx.dataI.hzdospro.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.gx.dataI.hzdospro.model.DosJz;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2020-03-13
 */
public interface IDosJzService extends IService<DosJz> {
    /**
     * 条件查询
     * @param parms 条件
     * @return
     */
    Page<DosJz> list(JSONObject parms);

    /**
     * 添加记录
     * @param entity
     * @return
     */
    int save(DosJz entity);

    /**
     * 根据主键删除
     * @param pks
     * @return
     */
    int delete(Serializable[] pks);

    /**
     * 更新实体
     * @param entity
     * @return
     */
    int update(DosJz entity);
}
