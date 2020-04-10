package com.gx.dataI.hzdospro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gx.dataI.hzdospro.model.Aaa;
import com.gx.dataI.hzdospro.mapper.AaaMapper;
import com.gx.dataI.hzdospro.service.IAaaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * <p>
 * 测试表 服务实现类
 * </p>
 *
 * @author xijie.yin
 * @since 2020-03-13
 */
@Service
public class AaaServiceImpl extends ServiceImpl<AaaMapper, Aaa> implements IAaaService {

    @Resource
    AaaMapper mapper;

    @Override
    public Page<Aaa> list(JSONObject parms) {
        int limit = parms.get("limit") == null ? 10 : Integer.parseInt(parms.get("limit").toString());
        int offset = parms.get("offset") == null ? 0 : Integer.parseInt(parms.get("offset").toString());
        Page<Aaa> page = new Page<Aaa>(offset, limit);
        EntityWrapper<Aaa> wrapper = new EntityWrapper<>();
        List<Aaa> lists = mapper.selectPage(page, wrapper);
        if (lists == null) {
            lists = new ArrayList<>();
        }
        page.setRecords(lists);
        return page;
    }

    @Override
    public int save(Aaa entity) {
    return mapper.insert(entity);
    }

    @Override
    public int delete(Serializable[] pks) {
    List<Serializable> list = Arrays.asList(pks);
    return mapper.deleteBatchIds(list);
    }

    @Override
    public int update(Aaa entity) {
    return mapper.updateById(entity);
    }
}
