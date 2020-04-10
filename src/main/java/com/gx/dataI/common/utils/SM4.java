package com.gx.dataI.common.utils;

import com.code.sm4.SM4Utils;

public class SM4 {
    private static SM4Utils sm4;
    private SM4(){}
    public static SM4Utils getSm4Utils(){
        if(sm4==null){
            sm4 = new SM4Utils();
            sm4.setSecretKey("19DF79076B42AD80");
            sm4.setIv("A6DF2FD576207A51");
        }
        return sm4;
    }
}
