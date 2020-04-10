package com.gx.dataI.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gx.dataI.api.model.SysUser;
import com.gx.dataI.api.mapper.SysUserMapper;
import com.gx.dataI.api.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gx.dataI.common.utils.StringUtil;
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
 * @author XiJieYin
 * @since 2020-03-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    SysUserMapper mapper;

    @Override
    public SysUser checkUser(String loginId, String password) {
        if(!StringUtil.isNotBlank(loginId)||!StringUtil.isNotBlank(password)){
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setLoginId(loginId);
        sysUser.setPassword(StringUtil.encodeMD5(password));
        sysUser  = mapper.selectOne(sysUser);
        return sysUser;
    }
}
