package com.gx.dataI.gxinfozx.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gx.dataI.common.annotation.DataSource;
import com.gx.dataI.common.enums.DataSourceEnum;
import com.gx.dataI.gxinfozx.mapper.ZxClmbzdMapper;
import com.gx.dataI.gxinfozx.model.ZxClmbzd;
import com.gx.dataI.gxinfozx.service.IZxClmbzdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 材料模板字典表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-12-16
 */
@Service
public class ZxClmbzdServiceImpl extends ServiceImpl<ZxClmbzdMapper, ZxClmbzd> implements IZxClmbzdService {

    @Resource
    ZxClmbzdMapper zxClmbzdMapper;

    @Override
    @DataSource(DataSourceEnum.DB2)
    public Page<ZxClmbzd> listClmb(Map<String, Object> parms) {
        int limit = parms.get("limit")==null?10:Integer.parseInt(parms.get("limit").toString());
        int offset = parms.get("offset")==null?1:Integer.parseInt(parms.get("offset").toString());
        Page<ZxClmbzd> zxClmbzdPage = new Page<>(offset, limit);
        EntityWrapper<ZxClmbzd> zxClmbzdEntityWrapper = new EntityWrapper<>();
        List<ZxClmbzd> zxClmbzds = zxClmbzdMapper.selectPage(zxClmbzdPage,zxClmbzdEntityWrapper);
        if (zxClmbzds==null){
            zxClmbzds = new ArrayList<>();
        }
        zxClmbzdPage.setRecords(zxClmbzds);
        return zxClmbzdPage;
    }
}
