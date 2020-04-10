package com.gx.dataI.hzdospro.controller;


import com.gx.dataI.hzdospro.model.DosJz;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.gx.dataI.common.enums.CodeEnum;
import com.gx.dataI.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
//引入Service
import com.gx.dataI.hzdospro.service.IDosJzService;

import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2020-03-13
 */
@Api(value = " 前端控制器")
@Controller
@RequestMapping("/dosJz")
public class DosJzController {

    @Autowired
    private IDosJzService service;

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = " 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "jsonData",value = "get请求传的参数接收字符串，post请求也可以通过url的方式传",dataType = "string"),
            @ApiImplicitParam(paramType = "body",name = "parms",value = "post请求传的参数接收的是JSON对象，不能通过get请求传",dataType = "string")})
    public Result listDosJz(@RequestParam(required = false) String jsonData, @RequestBody(required = false) JSONObject parms){
        if(jsonData!=null&&parms==null){
            parms = JSONObject.parseObject(jsonData);
        }
        if (parms==null){
            parms = new JSONObject();
        }
        return new Result(CodeEnum.SUCCESS.getCode(),service.list(parms));
    }

    @ResponseBody
    @RequestMapping(value = "/save",method = {RequestMethod.POST})
    @ApiOperation(value = " 添加")
    public Result save(DosJz entity){
        return service.save(entity)>0?new Result(CodeEnum.SUCCESS.getCode(),"添加成功！"):new Result(CodeEnum.LOGIN_EXCEPTION.getCode(),"添加失败！");
    }
    @ResponseBody
    @RequestMapping(value = "/delete",method = {RequestMethod.POST})
    @ApiOperation(value = " 删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form",name = "pks",value = "添加的id，用逗号隔开",dataType = "string",required = true)})
    public Result delete(@RequestParam(required = true) Serializable[] pks){
        return service.delete(pks)>0?new Result(CodeEnum.SUCCESS.getCode(),"删除成功！"):new Result(CodeEnum.LOGIN_EXCEPTION.getCode(),"删除失败！");
    }
    @ResponseBody
    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    @ApiOperation(value = " 修改")
    public Result update(DosJz entity){
        return service.update(entity)>0?new Result(CodeEnum.SUCCESS.getCode(),"修改成功！"):new Result(CodeEnum.LOGIN_EXCEPTION.getCode(),"修改失败！");
    }
}

