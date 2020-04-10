package com.gx.dataI.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.gx.dataI.api.service.IUploadService;
import com.gx.dataI.common.annotation.Log;
import com.gx.dataI.common.enums.OperateType;
import com.gx.dataI.common.utils.CRC16Util;
import com.gx.dataI.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Api(value = "上传接口",description = "用于上传图片")
@RestController
public class UploadController {

    @Resource
    private IUploadService service;

    @Log(msg = "上传图片",operate = OperateType.UPLOAD)
    @RequestMapping(value = "uploadFile",method = {RequestMethod.POST})
    @ApiOperation(value = " 上传图片(常口)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "crcCode",value = "使用CRC-16/Modbus校验，文件数据2字节校验码的16进制字符串",dataType = "string",required = true)})
    public R uploadFile(@RequestParam(required = true) String crcCode,@ApiParam(value = "上传的文件" ,required = true) MultipartFile file){
        if(crcCode==null){
            return new R().err("文件校验码不能为空！");
        }
        if(file==null){
            return new R().err("文件不能为空！");
        }
        try {
            if(!CRC16Util.getCRC(file.getBytes()).equalsIgnoreCase(crcCode)){
                return new R().err("文件校验失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = service.uploadFile(file);
        return url==null?new R().err():new R().ok().push("msg",url);
    }


    @Log(msg = "获取CRC校验码",operate = OperateType.UPLOAD)
    @RequestMapping(value = "getCRCCode",method = {RequestMethod.POST})
    @ApiOperation(value = " 获取CRC校验码(常口)")
    public R getCRCCode(@ApiParam(value = "上传的文件" ,required = true) MultipartFile file){
        if(file==null){
            return new R().err("文件不能为空！");
        }
        try {
            String code = CRC16Util.getCRC(file.getBytes());
            return new R().ok().push("msg",code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new R().err();
    }
}
