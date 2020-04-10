package com.gx.dataI;

import com.code.sm4.SM4Utils;
import com.gx.dataI.common.utils.StringUtil;

public class Demo {
    public static void main(String[] args) {

//        SM4Utils sm4 = new SM4Utils();
//        sm4.setSecretKey("19DF79076B42AD80");
//        sm4.setIv("A6DF2FD576207A51");
//        String [] fileGroupPath = new String[]{"group1","M00/00/00/wKgSal5szkiAHR3tAAJA_YLy1U0866.jpg"};
//        String org = StringUtil.join(fileGroupPath,"/");
//        String pass = sm4.encryptData_CBC(org);
//        System.out.println(pass);
//        String fileName = sm4.decryptData_CBC(pass);
//        System.out.println(fileName);
//        String group = fileName.substring(0,fileName.indexOf("/"));
//        String dfsPath = fileName.substring(fileName.indexOf("/")+1);
//        System.out.println(group);
//        System.out.println(dfsPath);
        String s = "--datai.allow.swagger.addres=192.168.18.76";
        System.out.println(s.substring(s.indexOf("=")+1));
    }
}
