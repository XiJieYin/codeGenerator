package com.gx.dataI.common.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FastDFSUtil {

    private static final Logger log = LoggerFactory.getLogger(FastDFSUtil.class);
    //storage server 端口
    private static int STORAGE_SERVER_PORT = 23000;
    //storage server storage在存储文件时支持多路径，默认只设置一个
    //配置多个store_path路径，从0开始，如果store_path0不存在，则base_path必须存在
    private static int STORAGE_SERVER_STORE_PATH = 0;
    //storage server 的组名
    private static String GROUP_NAME = "group1";
    //nginx代理的路径
    private static String NGINX_PATH = "http://192.168.18.106:18081/";
    private static final String CONF_FILENAME = "application.properties";

    /**
     * 拿到Nginx的路径
     * @return
     */
    public static String getNginxPath() {
        return NGINX_PATH;
    }

    public static String getGroupName() {
        return GROUP_NAME;
    }

    static{
        InputStream inStream = FastDFSUtil.class.getResourceAsStream("/application.properties");
        Properties prop = new Properties();
        try {
            prop.load(inStream);
            STORAGE_SERVER_PORT = Integer.parseInt(prop.getProperty("dfs.STORAGE_SERVER_PORT"));
            STORAGE_SERVER_STORE_PATH = Integer.parseInt(prop.getProperty("dfs.STORAGE_SERVER_STORE_PATH"));
            GROUP_NAME = prop.getProperty("dfs.GROUP_NAME");
            NGINX_PATH = prop.getProperty("dfs.NGINX_PATH");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带有防盗链的下载
     *
     * @param fileGroup       文件组名
     * @param remoteFileName  远程文件名称
     * @return 完整的URL地址
     */
    public static String autoDownloadWithToken(String fileGroup, String remoteFileName) throws Exception {
        StorageClient storageClient = getStorageClient(fileGroup);
        int ts = (int) (System.currentTimeMillis() / 1000);
        String token = ProtoCommon.getToken(remoteFileName, ts, ClientGlobal.getG_secret_key());
        return NGINX_PATH + "/" + fileGroup + "/" + remoteFileName + "?token=" + token + "&ts=" + ts;
    }

    /**
     * FastDFS实现文件下载
     * @param group 组名
     * @param filePath 文件名，就是上传返回的数组的第二个字符串
     */
    public static byte[] fastDFSDownload(String group,String filePath) {
        try {
            StorageClient storageClient = getStorageClient(group);
            byte[] b = storageClient.download_file(group, filePath);
            if (b == null) {
                throw new IOException("文件" + filePath + "不存在");
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * FastDFS获取将上传文件信息
     * @param group 组名
     * @param filePath 文件名，就是上传返回的数组的第二个字符串
     */
    public static String fastDFSGetFileInfo(String group,String filePath) {
        try {
            StorageClient storageClient = getStorageClient(group);
            FileInfo fi = storageClient.get_file_info("group1", filePath);
            if (fi == null) {
                throw new IOException("文件" + filePath + "不存在");
            }
            log.debug("文件信息=" + fi.toString());
            return fi.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * FastDFS获取文件名称
     * @param group 组名
     * @param filePath 文件名，就是上传返回的数组的第二个字符串
     */
    public static String fastDFSGetFileName(String group,String filePath) {
        try {
            StorageClient storageClient = getStorageClient(group);
            NameValuePair[] nvps = storageClient.get_metadata("group1", filePath);
            if (nvps == null) {
                throw new IOException("文件" + filePath + "不存在");
            }

            log.debug(nvps[0].getName() + "." + nvps[0].getValue());
            return nvps[0].getName() + "." + nvps[0].getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * FastDFS实现删除文件
     * @param group 组名
     * @param filePath 文件名，就是上传返回的数组的第二个字符串
     */
    public static int fastDFSDelete(String group,String filePath) {
        try {
            StorageClient storageClient = getStorageClient(group);
            int i = storageClient.delete_file("group1", filePath);
            log.debug(i == 0 ? "删除成功" : "删除失败:" + i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 上传文件
     * @param group 组名
     * @param byteFile 文件字节数据
     * @param ext_file 扩展名
     * @return
     */
    public static String[] uploadFile(String group,byte[] byteFile, String ext_file) {

        // 拼接服务区的文件路径
        StringBuffer sbPath = new StringBuffer();
        sbPath.append(NGINX_PATH);
        sbPath.append("/");
        try {
            StorageClient storageClient = getStorageClient(group);

            //利用字节流上传文件
//            NameValuePair[] nvps = new NameValuePair[1];
//            nvps[0] = new NameValuePair(file_Name, ext_file);
            String[] strings = storageClient.upload_file(byteFile, ext_file, null);

            if(strings!=null){
                sbPath.append(StringUtil.join(strings,"/"));
            }

            log.debug("上传路径=" + sbPath.toString());
            return strings;
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return null;
//        return sbPath.toString();
    }

    /**
     * 获取Storage客户端
     * @param group 组名
     * @return
     */
    private static StorageClient getStorageClient(String group){
        try {
            //加载FastDFS配置
            ClientGlobal.initByProperties(CONF_FILENAME);
            //FastDFS服务器，tracker和Stroage
            TrackerClient trackerClient = new TrackerClient();;
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            String storageServerIp = getStorageServerIp(group,trackerClient, trackerServer);

            StorageServer storageServer = getStorageServer(storageServerIp);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            return storageClient;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到Storage服务
     *
     * @param storageIp
     * @return 返回Storage服务
     */
    private static StorageServer getStorageServer(String storageIp) {
        StorageServer storageServer = null;
        if (storageIp != null && !("").equals(storageIp)) {
            try {
                // ip port store_path下标
                storageServer = new StorageServer(storageIp, STORAGE_SERVER_PORT, STORAGE_SERVER_STORE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.debug("——storage server生成");
        return storageServer;
    }

    /**
     * 获得可用的storage IP
     *
     * @param group 组名
     * @param trackerClient
     * @param trackerServer
     * @return 返回storage IP
     */
    private static String getStorageServerIp(String group,TrackerClient trackerClient, TrackerServer trackerServer) {
        String storageIp = null;
        if (trackerClient != null && trackerServer != null) {
            try {
                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, group);
                storageIp = storageServer.getInetSocketAddress().getAddress().getHostAddress();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
        log.debug("——获取组中可用的storage IP——" + storageIp);
        return storageIp;
    }
}
