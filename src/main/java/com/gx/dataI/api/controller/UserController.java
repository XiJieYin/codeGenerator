package com.gx.dataI.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.gx.dataI.api.es.entity.User;
import com.gx.dataI.api.es.repository.UserRepository;
import com.gx.dataI.common.utils.R;
import com.gx.dataI.common.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.UUID;

@Api(value = "用户管理",description = "用于管理Api接口的用户")
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository repository;

    @ResponseBody
    @RequestMapping(value = "add",method = {RequestMethod.POST})
    @ApiOperation(value = "添加用户")
    public R add(User user){
        if (user==null){
            return new R().parmsNullErr();
        }
        if (user.getUserName()==null||user.getPassword()==null){
            return new R().err("用户名和密码不为能空");
        }
        Optional<User> savedUser = repository.findByUserName(user.getUserName());
        if(savedUser.isPresent()){
            return new R().err("用户名重复");
        }
        user.setGuid(UUID.randomUUID().toString());
        user.setPassword(StringUtil.encodeMD5(user.getPassword()));
        repository.save(user);
        return new R().ok().push("user",JSONObject.parseObject(JSONObject.toJSONString(user)));
    }

    @ResponseBody
    @RequestMapping(value = "edit",method = {RequestMethod.POST})
    @ApiOperation(value = "修改用户")
    public R edit(User user){
        if (user==null){
            return new R().parmsNullErr();
        }
        if(user.getGuid()==null){
            return new R().err("用户guid不能为空");
        }
        if (user.getUserName()==null||user.getPassword()==null){
            return new R().err("用户名和密码不为能空");
        }
        Optional<User> savedUser = repository.findById(user.getGuid());
        if(!savedUser.isPresent()){
            return new R().err("用户不存在");
        }
        if(savedUser.get().getPassword()!=StringUtil.encodeMD5(user.getPassword())){
            //如果用户密码与之前不同就需要重新加密密码
            user.setPassword(StringUtil.encodeMD5(user.getPassword()));
        }
        repository.save(user);
        savedUser = repository.findById(user.getGuid());
        return new R().ok().push("user",JSONObject.parseObject(JSONObject.toJSONString(savedUser.get())));
    }
}
