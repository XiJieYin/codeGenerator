package com.gx.dataI.api.controller;

import com.gx.dataI.api.es.entity.Log;
import com.gx.dataI.api.es.entity.LogDA;
import com.gx.dataI.api.service.ILogService;
import com.gx.dataI.common.utils.DateUtil;
import com.gx.dataI.common.utils.FileUtil;
import com.gx.dataI.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Api(value = "日志管理接口",description = "用于管理日志")
@Controller
@RequestMapping("log")
public class LogController {

    @Autowired
    ILogService iLogService;

    @ResponseBody
    @RequestMapping(value = "list",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "查询日志")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value = "页码从0开始",dataType = "int")
            ,@ApiImplicitParam(paramType = "query",name = "size",value = "分页大小",dataType = "int")
            ,@ApiImplicitParam(paramType = "query",name = "userName",value = "操作用户",dataType = "string")
            ,@ApiImplicitParam(paramType = "query",name = "operation",value = "操作类型",dataType = "int")
            ,@ApiImplicitParam(paramType = "form",name = "operateTimeStart",value = "操作时间开始 格式：yyyy-MM-dd HH:mm:ss",dataType = "date")
            ,@ApiImplicitParam(paramType = "form",name = "operateTimeEnd",value = "操作时间结束 格式：yyyy-MM-dd HH:mm:ss",dataType = "date")})
    public R list(Integer page,Integer size,String userName, Integer operation, Date operateTimeStart, Date operateTimeEnd){
        if(page!=null&&size!=null){
            //需要分页
            Page<Log> logs = iLogService.listPage(page,size,userName,operation,operateTimeStart,operateTimeEnd);
            return new R().ok().push("logs",logs);
        }else{
            //不需要分页
            List<Log> logs = iLogService.list(userName,operation,operateTimeStart,operateTimeEnd);
            return new R().ok().push("logs",logs);
        }
    }

    @RequestMapping(value = "exportExcel",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "导出日志")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "userName",value = "操作用户",dataType = "string")
            ,@ApiImplicitParam(paramType = "query",name = "operation",value = "操作类型",dataType = "int")
            ,@ApiImplicitParam(paramType = "form",name = "operateTimeStart",value = "操作时间开始 格式：yyyy-MM-dd HH:mm:ss",dataType = "date")
            ,@ApiImplicitParam(paramType = "form",name = "operateTimeEnd",value = "操作时间结束 格式：yyyy-MM-dd HH:mm:ss",dataType = "date")})
    public void exportExcel(String userName, Integer operation, Date operateTimeStart, Date operateTimeEnd,HttpServletRequest request,HttpServletResponse response){
        List<Log> logs = iLogService.list(userName,operation,operateTimeStart,operateTimeEnd);
        String fileName = "日志信息（" + DateUtil.date2String(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS) + "）统计.xls";
        FileUtil.exportExcel(logs, "日志信息统计", "Sheet1", Log.class, fileName, response);
    }

    @ResponseBody
    @RequestMapping(value = "delete",method = {RequestMethod.POST,RequestMethod.DELETE})
    @ApiOperation(value = "删除日志")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "userName",value = "操作用户",dataType = "string")})
    public R delete(String userName){
        return iLogService.delete(userName)>0?new R().ok():new R().err();
    }



    @ResponseBody
    @RequestMapping(value = "listDA",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "查询日志(档案)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value = "页码从0开始",dataType = "int")
            ,@ApiImplicitParam(paramType = "query",name = "size",value = "分页大小",dataType = "int")
            ,@ApiImplicitParam(paramType = "query",name = "operation",value = "操作类型",dataType = "int")
            ,@ApiImplicitParam(paramType = "form",name = "operateTimeStart",value = "操作时间开始 格式：yyyy-MM-dd HH:mm:ss",dataType = "date")
            ,@ApiImplicitParam(paramType = "form",name = "operateTimeEnd",value = "操作时间结束 格式：yyyy-MM-dd HH:mm:ss",dataType = "date")})
    public R listDA(Integer page,Integer size,Integer operation, Date operateTimeStart, Date operateTimeEnd){
        if(page!=null&&size!=null){
            //需要分页
            Page<LogDA> logs = iLogService.listDAPage(page,size,operation,operateTimeStart,operateTimeEnd);
            return new R().ok().push("logs",logs);
        }else{
            //不需要分页
            List<LogDA> logs = iLogService.listDA(operation,operateTimeStart,operateTimeEnd);
            return new R().ok().push("logs",logs);
        }
    }

    @RequestMapping(value = "exportDAExcel",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "导出日志（档案）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "operation",value = "操作类型",dataType = "int")
            ,@ApiImplicitParam(paramType = "form",name = "operateTimeStart",value = "操作时间开始 格式：yyyy-MM-dd HH:mm:ss",dataType = "date")
            ,@ApiImplicitParam(paramType = "form",name = "operateTimeEnd",value = "操作时间结束 格式：yyyy-MM-dd HH:mm:ss",dataType = "date")})
    public void exportDAExcel(String userName, Integer operation, Date operateTimeStart, Date operateTimeEnd,HttpServletRequest request,HttpServletResponse response){
        List<LogDA> logs = iLogService.listDA(operation,operateTimeStart,operateTimeEnd);
        String fileName = "日志（档案）信息（" + DateUtil.date2String(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS) + "）统计.xls";
        FileUtil.exportExcel(logs, "日志信息（档案）统计", "Sheet1", Log.class, fileName, response);
    }

    @ResponseBody
    @RequestMapping(value = "deleteDA",method = {RequestMethod.POST,RequestMethod.DELETE})
    @ApiOperation(value = "删除日志(档案)")
    public R deleteDA(){
        return iLogService.deleteDA()>0?new R().ok():new R().err();
    }

}
