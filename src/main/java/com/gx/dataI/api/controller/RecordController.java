package com.gx.dataI.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.gx.dataI.common.annotation.Log;
import com.gx.dataI.common.enums.OperateType;
import com.gx.dataI.common.enums.RequestConst;
import com.gx.dataI.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "记录操作接口",description = "数据记录的增，删，改操作")
@Controller
public class RecordController {

    @Log(msg = "上传记录",operate = OperateType.INSERT)
    @RequestMapping(value = "saveRecord",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "记录操作接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "jsonData",value = "get请求传的参数接收字符串，post请求也可以通过url的方式传",dataType = "string"),
            @ApiImplicitParam(paramType = "body",name = "parms",value = "post请求传的参数接收的是JSON对象，不能通过get请求传",dataType = "string")})
    public R saveRecord(@RequestParam(required = false) String jsonData, @RequestBody(required = false) JSONObject parms){
        if(jsonData!=null&&parms==null){
            parms = JSONObject.parseObject(jsonData);
        }
        if (parms==null){
            return new R().parmsNullErr();
        }
        String systype = parms.getString(RequestConst.SYSTYPE.getVal());//系统类型
        String type = parms.getString(RequestConst.TYPE.getVal());//业务类型（QC001、身份证 QC002、户口本...待定）·
        String updatetype = parms.getString(RequestConst.UPDATETYPE.getVal());//业务类型（C 插入 U 更新 D 删除 ）·
        String isdelfile = parms.getString(RequestConst.ISDELFILE.getVal());//是否同时删除图片，默认为false·
        JSONObject record = JSONObject.parseObject(JSONObject.toJSONString(parms.getString(RequestConst.RECORD.getVal())));//记录体
        if(systype==null){
            return new R().parmsNullErr().push("缺少字段",RequestConst.SYSTYPE.getVal());
        }
        return new R().ok();
    }
}
