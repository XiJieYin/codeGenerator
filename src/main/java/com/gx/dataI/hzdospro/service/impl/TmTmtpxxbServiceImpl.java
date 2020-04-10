package com.gx.dataI.hzdospro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gx.dataI.hzdospro.model.TmTmtpxxb;
import com.gx.dataI.hzdospro.mapper.TmTmtpxxbMapper;
import com.gx.dataI.hzdospro.service.ITmTmtpxxbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 卷宗图片信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-03-13
 */
@Service
public class TmTmtpxxbServiceImpl extends ServiceImpl<TmTmtpxxbMapper, TmTmtpxxb> implements ITmTmtpxxbService {

    @Resource
    TmTmtpxxbMapper mapper;

    @Override
    public Page<TmTmtpxxb> list(JSONObject parms) {
        int limit = parms.get("limit") == null ? 10 : Integer.parseInt(parms.get("limit").toString());
        int offset = parms.get("offset") == null ? 0 : Integer.parseInt(parms.get("offset").toString());
        Page<TmTmtpxxb> page = new Page<TmTmtpxxb>(offset, limit);
        EntityWrapper<TmTmtpxxb> wrapper = new EntityWrapper<>();
        List<TmTmtpxxb> lists = mapper.selectPage(page, wrapper);
        if (lists == null) {
            lists = new ArrayList<>();
        }
        page.setRecords(lists);
        return page;
    }
}
