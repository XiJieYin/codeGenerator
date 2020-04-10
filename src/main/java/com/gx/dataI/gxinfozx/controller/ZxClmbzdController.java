package com.gx.dataI.gxinfozx.controller;


import com.gx.dataI.common.enums.CodeEnum;
import com.gx.dataI.common.utils.Result;
import com.gx.dataI.gxinfozx.service.IZxClmbzdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 材料模板字典表 前端控制器
 * </p>
 *
 * @author 
 * @since 2019-12-16
 */
@Controller
@RequestMapping("/zxClmbzd")
@Api(value = "材料模板字典表 前端控制器")
public class ZxClmbzdController {

    @Autowired
    IZxClmbzdService zxClmbzdService;

    @ResponseBody
    @RequestMapping(value="/listClmb",method = RequestMethod.GET)
    @ApiOperation(value = "材料模板字典列表")
    @ApiImplicitParam(paramType = "query",name = "parms",value = "键值对，form-data",dataType = "string")
    public Result listClmb(@RequestParam Map<String,Object> parms){
        return new Result(CodeEnum.SUCCESS.getCode(),zxClmbzdService.listClmb(parms));
    }
}

