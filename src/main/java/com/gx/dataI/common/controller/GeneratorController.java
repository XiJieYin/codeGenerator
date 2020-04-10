package com.gx.dataI.common.controller;

import com.gx.dataI.common.utils.Generator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "代码生成器",description = "代码生成器")
@Controller
@RequestMapping(value = "generator")
public class GeneratorController {

    @RequestMapping(value = "generate",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "卷宗图片信息表 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "packageName",value = "包名：例如：com.gx.dataI.system",required = true),
            @ApiImplicitParam(paramType = "query",name = "author",value = "作者",defaultValue = ""),
            @ApiImplicitParam(paramType = "query",name = "database",value = "数据库用户名",required = true),
            @ApiImplicitParam(paramType = "query",name = "password",value = "数据库密码",required = true),
            @ApiImplicitParam(paramType = "query",name = "tablenames",value = "表名，逗号隔开表名",required = true)
    })
    @ResponseBody
    public int generate(@RequestParam(required = true) String packageName,
                         @RequestParam(defaultValue = "") String author,
                         @RequestParam(required = true) String database,
                         @RequestParam(required = true) String password,
                         @RequestParam(required = true) String... tablenames){
        boolean serviceNameStartWithI = true;//auth -> UserService, 设置成true: auth -> IUserService
        Generator.generateByTables(serviceNameStartWithI, packageName, author, database,password, tablenames);
        System.out.println("completed...");
        return 1;
    }
}
