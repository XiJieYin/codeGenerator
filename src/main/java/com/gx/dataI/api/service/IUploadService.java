package com.gx.dataI.api.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    /**
     * 用于常口上传
     * 1、上传文件到DFS
     * 2、DFS上传后会返回一个组名和乱码的路径
     * 3、将这个组名和路径用SM4加密返回
     * @param file 文件
     * @return
     */
    String uploadFile(MultipartFile file);

    /**
     * 用于档案上传
     * 1、上传文件到DFS
     * 2、DFS上传后会返回一个组名和乱码的路径
     * 3、将这个文件名和乱码路径做映射
     * 4、将映射关系保存到ES上     *
     * @param fileName 文件名
     * @param file 文件
     * @return 1成功，0失败
     */
    int uploadFile(String fileName, MultipartFile file);
}
