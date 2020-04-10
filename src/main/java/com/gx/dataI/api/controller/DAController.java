package com.gx.dataI.api.controller;

import com.gx.dataI.api.es.entity.ImgDA;
import com.gx.dataI.api.es.repository.ImgDARepository;
import com.gx.dataI.api.service.IUploadService;
import com.gx.dataI.common.annotation.Log;
import com.gx.dataI.common.enums.OperateSYS;
import com.gx.dataI.common.enums.OperateType;
import com.gx.dataI.common.utils.CRC16Util;
import com.gx.dataI.common.utils.FastDFSUtil;
import com.gx.dataI.common.utils.R;
import com.gx.dataI.common.utils.SM4;
import io.swagger.annotations.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@Api(value = "档案文件接口",description = "用于档案上传、下载图片")
@Controller
@RequestMapping("da")
public class DAController {

    @Value("${fastdfs.da.group}")
    private String group;

    @Resource
    private IUploadService service;
    @Autowired
    private ImgDARepository imgDARepository;

    @Log(operate = OperateType.UPLOAD,msg = "上传图片",operateSYS = OperateSYS.DA)
    @ResponseBody
    @RequestMapping(value = "post",method = {RequestMethod.POST})
    @ApiOperation(value = " 上传图片(档案)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "filename",value = "图片保存时文件名称，文件名称的结构为：系统类型 + “/” + 业务类型 + “/” + yyyyMMdd/文件名（带后缀名）注：系统类型如RKSYS（常口）",dataType = "string",required = true)})
    public R post(@RequestParam(required = false) String filename, @ApiParam(value = "上传的文件" ,required = true) MultipartFile photo){
        if(filename==null){
            filename = photo.getOriginalFilename();
            if(filename==null){
                return new R().err("文件名不能为空！");
            }
        }
        if(photo==null){
            return new R().err("文件不能为空！");
        }
        Optional<ImgDA> imgDASaved = imgDARepository.findById(filename);
        if(imgDASaved.isPresent()){
            FastDFSUtil.fastDFSDelete(group,imgDASaved.get().getPath());
        }
        return service.uploadFile(filename,photo)>0?new R().ok():new R().err();
    }

    /**
     * URL查看图片(档案)
     * @param filename 文件名
     * @param request
     * @param response 打印输出图片
     */
//    @Log(operate = OperateType.UPLOAD,msg = "URL查看图片",operateSYS = OperateSYS.DA)
    @RequestMapping(value="get",method = {RequestMethod.GET})
    @ApiOperation(value = "URL查看图片(档案)")
    public void url(@RequestParam String filename, HttpServletRequest request, HttpServletResponse response){
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
        if(filename!=null){
            Optional<ImgDA> imgDA = imgDARepository.findById(filename);
            if(imgDA.isPresent()){
                byte [] binaryFile = FastDFSUtil.fastDFSDownload(group,imgDA.get().getPath());
                if(binaryFile!=null){
                    //设置返回的文件类型
                    response.setContentType("image/jpeg");
                    OutputStream os = null;
                    try {
                        os = response.getOutputStream();
                        os.write(binaryFile);
                        os.flush();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    //否则默认访问登录接口
                    try {
                        wrapper.sendError(404,"找不到图片！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                //否则默认访问登录接口
                try {
                    wrapper.sendError(404,"找不到图片！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            //否则默认访问登录接口
            try {
                wrapper.sendError(404,"找不到图片！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
