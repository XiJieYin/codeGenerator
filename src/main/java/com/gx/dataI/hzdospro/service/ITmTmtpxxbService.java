package com.gx.dataI.hzdospro.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.gx.dataI.hzdospro.model.TmTmtpxxb;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 卷宗图片信息表 服务类
 * </p>
 *
 * @author 
 * @since 2020-03-13
 */
public interface ITmTmtpxxbService extends IService<TmTmtpxxb> {
        /**
         * 条件查询
         * @param parms 条件
         * @return
         */
        Page<TmTmtpxxb> list(JSONObject parms);
}
