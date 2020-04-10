package com.gx.dataI.api.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.gx.dataI.api.model.SysUser;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XiJieYin
 * @since 2020-03-21
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser checkUser(String loginId,String password);
}
