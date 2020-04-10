package com.gx.dataI.api.service.impl;

import com.gx.dataI.api.es.entity.ImgDA;
import com.gx.dataI.api.es.entity.ImgOut;
import com.gx.dataI.api.es.entity.User;
import com.gx.dataI.api.es.repository.ImgDARepository;
import com.gx.dataI.api.es.repository.ImgOutRepository;
import com.gx.dataI.api.service.IUploadService;
import com.gx.dataI.common.utils.FastDFSUtil;
import com.gx.dataI.common.utils.SM4;
import com.gx.dataI.common.utils.SessionUtils;
import com.gx.dataI.common.utils.StringUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    ImgDARepository imgDARepository;
    @Autowired
    ImgOutRepository imgOutRepository;


    @Value("${dfs.STORAGE_SERVER_PORT}")
    private String storageServerPort;
    @Value("${dfs.STORAGE_SERVER_STORE_PATH}")
    private String storageServerStorePath;
    @Value("${dfs.GROUP_NAME}")
    private String groupName;
    @Value("${dfs.NGINX_PATH}")
    private String nginxPath;

    @Override
    public String uploadFile(MultipartFile file) {
        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        String file_Name = file.getOriginalFilename().split("\\.")[0];
        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] fileGroupPath = FastDFSUtil.uploadFile(FastDFSUtil.getGroupName(),bytes, ext_Name);
        if(fileGroupPath!=null){
            String fileURL = StringUtil.join(fileGroupPath,"/");
            //加密
            fileURL =  SM4.getSm4Utils().encryptData_CBC(fileURL);
            //转base64 URL安全的字符串
            String safeURL = Base64.encodeBase64URLSafeString(fileURL.getBytes());
            if(SessionUtils.getUserName()!=null){
                User currUser = SessionUtils.getUser();
                if(currUser!=null){
                    ImgOut imgOut = new ImgOut();
                    imgOut.setFileName(safeURL);
                    imgOut.setGroup(fileGroupPath[0]);
                    imgOut.setPath(fileGroupPath[0]);
                    imgOut.setStatus(0);
                    imgOut.setUpdateTime(new Date());
                    imgOut.setUserGuid(currUser.getGuid());
                    imgOut.setUserName(currUser.getUserName());
                    imgOutRepository.save(imgOut);
                }
            }
            return safeURL;
        }
        return null;
    }

    @Override
    public int uploadFile(String fileName, MultipartFile file) {
        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        String file_Name = file.getOriginalFilename().split("\\.")[0];

        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String [] fileGroupPath= FastDFSUtil.uploadFile(FastDFSUtil.getGroupName(),bytes, ext_Name);
        if(fileGroupPath!=null){
            ImgDA imgDA = new ImgDA(fileName,fileGroupPath[0],fileGroupPath[1],new Date());
            imgDARepository.save(imgDA);
            return 1;
        }
        return 0;
    }
}
