package com.gx.dataI.api.controller;

import com.gx.dataI.common.annotation.Log;
import com.gx.dataI.common.enums.OperateType;
import com.gx.dataI.common.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@Api(value = "下载接口", description = "用于转发访问图片，下载文件，记录")
@Controller
public class DownloadController {


    @Value("${dfs.STORAGE_SERVER_PORT}")
    private String storageServerPort;
    @Value("${dfs.STORAGE_SERVER_STORE_PATH}")
    private String storageServerStorePath;
    @Value("${dfs.GROUP_NAME}")
    private String groupName;
    @Value("${dfs.NGINX_PATH}")
    private String nginxPath;

    /**
     *URL查看图片
     * @param fileName 文件名
     * @param request
     * @param response 打印输出图片
     */
//    @Log(operate = OperateType.ACCESS,msg = "URL查看图片")//查看图片就不用了吧，每天那么多人看，还写日志浪费空间
    @RequestMapping(value="url/{fileName}",method = {RequestMethod.GET})
    @ApiOperation(value = " URL查看图片")
    public void url(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response){
        if(fileName!=null){
            //先转base64
            fileName = new String(Base64.decodeBase64(fileName));
            //再解密
            fileName = SM4.getSm4Utils().decryptData_CBC(fileName);
            String group = fileName.substring(0,fileName.indexOf("/"));
            String dfsPath = fileName.substring(fileName.indexOf("/")+1);
//            dfsPath = "M00/00/00/wKgS-158DLeAOEwmAAwN-iWXVmY877.gif";
            //加密的
            String enableURL = null;
            try {
                enableURL = FastDFSUtil.autoDownloadWithToken(group,dfsPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //未加密
//            String enableURL = FastDFSUtil.getNginxPath() + "/" + group + "/" + dfsPath;
            try {
                if(enableURL!=null){
                    response.sendRedirect(enableURL);
                }else{
                    HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
                    wrapper.sendError(404,"找不到图片！");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //下面的注释掉的代码是打印输出图片
//            String realPath = images.get().getDfsPath();
//            byte [] binaryFile = FastDFSUtil.fastDFSDownload(realPath);
//            if(binaryFile!=null){
//                //设置返回的文件类型
//                response.setContentType("image/jpeg");
//                OutputStream os = null;
//                try {
//                    os = response.getOutputStream();
//                    os.write(binaryFile);
//                    os.flush();
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }else{
//                HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
//                //否则默认访问登录接口
//                try {
//                    wrapper.sendError(404,"找不到图片！");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }else{
            HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
            //否则默认访问登录接口
            try {
                wrapper.sendError(404,"找不到图片！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param fileName 图片文件名
     * @return
     */
    @Log(msg = "下载文件",operate = OperateType.DOWNLOAD)
    @RequestMapping(value="download/{fileName}",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = " 下载图片数据(常口)")
    @ResponseBody
    public R download(@PathVariable String fileName){
        if(fileName!=null){
            //先转base64
            fileName = new String(Base64.decodeBase64(fileName));
            //再解密
            fileName = SM4.getSm4Utils().decryptData_CBC(fileName);
            String group = fileName.substring(0,fileName.indexOf("/"));
            String dfsPath = fileName.substring(fileName.indexOf("/")+1);
            byte [] binaryFile = FastDFSUtil.fastDFSDownload(group,dfsPath);
            if(binaryFile!=null){
                return new R().ok().push("crcCode", CRC16Util.getCRC(binaryFile)).push("msg", Base64Utils.byte2Base64StringFun(binaryFile));
            }else{
                return new R().result("404","找不到图片！");
            }
        }else{
            return new R().result("404","找不到图片！");
        }
    }
}
