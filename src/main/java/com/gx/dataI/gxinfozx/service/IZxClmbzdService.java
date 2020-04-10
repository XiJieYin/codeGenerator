package com.gx.dataI.gxinfozx.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.gx.dataI.gxinfozx.model.ZxClmbzd;

import java.util.Map;

/**
 * <p>
 * 材料模板字典表 服务类
 * </p>
 *
 * @author 
 * @since 2019-12-16
 */
public interface IZxClmbzdService extends IService<ZxClmbzd> {

    Page<ZxClmbzd> listClmb(Map<String, Object> parms);
}
