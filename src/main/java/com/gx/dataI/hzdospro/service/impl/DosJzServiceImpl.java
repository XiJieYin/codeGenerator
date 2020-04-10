package com.gx.dataI.hzdospro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gx.dataI.hzdospro.model.DosJz;
import com.gx.dataI.hzdospro.mapper.DosJzMapper;
import com.gx.dataI.hzdospro.service.IDosJzService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2020-03-13
 */
@Service
public class DosJzServiceImpl extends ServiceImpl<DosJzMapper, DosJz> implements IDosJzService {

    @Resource
    DosJzMapper mapper;

    @Override
    public Page<DosJz> list(JSONObject parms) {
        int limit = parms.get("limit") == null ? 10 : Integer.parseInt(parms.get("limit").toString());
        int offset = parms.get("offset") == null ? 0 : Integer.parseInt(parms.get("offset").toString());
        Page<DosJz> page = new Page<DosJz>(offset, limit);
        EntityWrapper<DosJz> wrapper = new EntityWrapper<>();
        List<DosJz> lists = mapper.selectPage(page, wrapper);
        if (lists == null) {
            lists = new ArrayList<>();
        }
        page.setRecords(lists);
        return page;
    }

    @Override
    public int save(DosJz entity) {
        return mapper.insert(entity);
    }

    @Override
    public int delete(Serializable[] pks) {
        List<Serializable> list = Arrays.asList(pks);
        return mapper.deleteBatchIds(list);
    }

    @Override
    public int update(DosJz entity) {
        return mapper.updateById(entity);
    }
}
