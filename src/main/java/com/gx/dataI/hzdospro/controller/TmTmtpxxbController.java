package com.gx.dataI.hzdospro.controller;


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
import com.gx.dataI.hzdospro.service.ITmTmtpxxbService;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 卷宗图片信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-03-13
 */
@Api(value = "卷宗图片信息表 前端控制器")
@Controller
@RequestMapping("/tmTmtpxxb")
public class TmTmtpxxbController {

    @Autowired
    private ITmTmtpxxbService service;

    @ResponseBody
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "卷宗图片信息表 列表")
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
}

