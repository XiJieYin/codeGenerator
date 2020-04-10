package com.gx.dataI.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobAndString {

	
	// 将字CLOB转成STRING类型 
    public static String ClobToString(Clob clob) throws SQLException, IOException { 
    	
        String reString = ""; 
        java.io.Reader is = clob.getCharacterStream();// 得到流 
        BufferedReader br = new BufferedReader(is); 
        String s = br.readLine(); 
        StringBuffer sb = new StringBuffer(); 
        while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING 
            sb.append(s); 
            s = br.readLine(); 
        } 
        reString = sb.toString(); 
        return reString; 
    } 
    /**
 	 * 将blob转化为byte[],可以转化二进制流的
 	 * 
 	 * @param blob
 	 * @return
 	 */
 	public static  byte[] blobToBytes(Blob blob) {
 		InputStream is = null;
 		byte[] b = null;
 		try {
 			is = blob.getBinaryStream();
 			b = new byte[(int) blob.length()];
 			is.read(b);
 			return b;
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			try {
 				is.close();
 				is = null;
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
 		return b;
 	}
}
