package com.gx.dataI.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 字符串工具类
 */
public class StringUtil extends StringUtils {
	private static Logger logger = Logger.getLogger(StringUtil.class);
	
	private static final String UTF8 = "UTF-8"; 
	
	/**
	 * 创建UUID
	 * @return
	 */
	public static String createUUID(){									
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-","").toUpperCase();
	}
	
	/**
	 * 转码
	 * @param str
	 * @return
	 */
	public static String encode(String str){
		try {
			str = URLEncoder.encode(str, UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.error("转码异常：", e);
		}
		return str;
	}
	
	/**
	 * 解码
	 * @param str
	 * @return
	 */
	public static String decode(String str){
		try {
			if (StringUtil.isNotEmpty(str)){
				str = URLDecoder.decode(str, UTF8);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("解码异常：", e);
		}
		return str;
	}
	
	/**
	 * 两次解码
	 * @param str
	 * @return
	 */
	public static String doubleDecode(String str) {
		return decode(decode(str));
	}

	/**
	 * id字符串转成List
	 * @param idStr  e.g. 1,2,3,4
	 * @return 返回 List<Integer> (不会为null值)
	 */
	public static List<Integer> convertToList(String idStr) {
		ArrayList<Integer> ids = new ArrayList<Integer>();

		if ( StringUtils.isNotEmpty(idStr) ) {
			String[] idArray = idStr.split(",");
			for (String id : idArray) {
				// 判断id不为空
				if (StringUtils.isNotEmpty(id)) {
					ids.add(Integer.parseInt(id));
				}
			}
		}
		// 将此 ArrayList 实例的容量调整为列表的当前大小
		ids.trimToSize();
		
		return ids;
	}
	
	/**
	 * id字符串转成List
	 * @param idStr  e.g. 1,2,3,4
	 * @return 返回 List<Integer> (不会为null值)
	 */
	public static List<String> convertToStrList(String idStr) {
		ArrayList<String> ids = new ArrayList<String>();

		if ( StringUtils.isNotEmpty(idStr) ) {
			String[] idArray = idStr.split(",");
			for (String id : idArray) {
				// 判断id不为空
				if (StringUtils.isNotEmpty(id)) {
					ids.add(id);
				}
			}
		}
		// 将此 ArrayList 实例的容量调整为列表的当前大小
		ids.trimToSize();
		
		return ids;
	}
	
	/**
	 * id字符串转成List
	 * @param idStr  e.g. 1,2,3,4
	 * @return 返回 List<String> (不会为null值)
	 */
	public static List<String> convertToStringList(String idStr) {
		ArrayList<String> ids = new ArrayList<String>();

		if ( StringUtils.isNotEmpty(idStr) ) {
			String[] idArray = idStr.split(",");
			for (String id : idArray) {
				// 判断id不为空
				if (StringUtils.isNotEmpty(id)) {
					ids.add(id);
				}
			}
		}
		// 将此 ArrayList 实例的容量调整为列表的当前大小
		ids.trimToSize();
		
		return ids;
	}
	
	/**
	 * List<Integer>转成字符串
	 * @param ids
	 * @return 返回 e.g. 1,2,3,4 字符串
	 */
	public static String convertToArrayJson(List<Integer> ids) {
		StringBuffer sbIds = new StringBuffer("");
		
		if (null != ids && ids.size() > 0) {
			for (int id : ids) {
				sbIds.append(id).append(",");
			}
			int index = sbIds.lastIndexOf(",");
			sbIds = sbIds.replace(index, index+1, "");
		}
		
		return sbIds.toString();
	}
	
	/**
	 * List<String>转成字符串
	 * @param ids
	 * @return 返回 e.g. 1,2,3,4 字符串
	 */
	public static String convertToArrayJson4String(List<String> ids) {
		StringBuffer sbIds = new StringBuffer("");
		
		if (null != ids && ids.size() > 0) {
			for (String id : ids) {
				sbIds.append("\""+id+"\"").append(",");
			}
			int index = sbIds.lastIndexOf(",");
			sbIds = sbIds.replace(index, index+1, "");
		}
		
		return sbIds.toString();
	}
	
	/**
	 * 创建模糊SQL语句
	 * @param value
	 * @return
	 */
	public static String createLikeSql(String value){
		if (StringUtils.isNotEmpty(value)) {
			value = value.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
			value = "%"+value+"%";
		}
		return value;
	}
	
	/**
	 * 创建模糊SQL语句
	 * @param value
	 * @return
	 */
	public static String createRightLikeSql(String value){
		if (StringUtils.isNotEmpty(value)) {
			value = value.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
			value = value+"%";
		}
		return value;
	}
	
	/**
	 * 返回订单流水号
	 * @return 年月日时分秒毫秒4位随机数
	 */
	public static String returnOrderNumber(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int random=(int)(Math.random()*9000+1000);
		String number = format.format(new Date()) + random +"";
		return number;
	}
	
	public static String createNumber(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		int random=(int)(Math.random()*9000+1000);
		String number = format.format(new Date()) + random +"";
		return number;
	}
	
	/**
	 * 将NULL转换成""字符串
	 * @param value
	 * @return
	 */
	public static String convertNullValue(String value) {
		if (isEmpty(value)) {
			value = "";
		}
		return value;
	}
	
	/**
	 * 将Double转换成字符串
	 * @param value
	 * @return
	 */
	public static String valueOf(Double value) {
		if (null == value) {
			return "";
		}
		return String.valueOf(value);
	}
	
	/**
	 * 判断是否是数字
	 * @param value
	 * @return
	 */
	public static boolean isNumeric(String value){
		return value.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	public static boolean isInteger(String value){
		return value.matches("^[-+]?(([0-9]+))$");
	}
	
	/**
	 * 判断是否是为电话号码
	 * @param value
	 * @return
	 */
	public static boolean isPhone(String value){
		return value.matches("^(1[358]\\d{9})$|^((0(10|2[0-3]|[3-9]\\d{2}))?[1-9]\\d{6,7})$");
	}
	
	/**
	 * 字符串转换成浮点型
	 * @author chenshujie
	 * @param value
	 * @return "",null 转换成0d;
	 */
	public static double convertToDouble(String value){
		
		if (StringUtil.isEmpty(value)) {
			return 0d;
		}
		
		return Double.valueOf(value);
	}
	
	public static String encodingToUTF(String str) throws UnsupportedEncodingException{
		return new String(str.getBytes("iso-8859-1"),UTF8);
	}
	
	public static final String LIKE_SYMBOL = "%";
	/** 左模糊查询 */
	public static String LeftLike(String str) {
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return str + LIKE_SYMBOL;
	}

	/** 右模糊查询 */
	public static String RightLike(String str) {
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return LIKE_SYMBOL + str;
	}

	/** 模糊查询 */
	public static String Like(String str) {
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return LIKE_SYMBOL + str + LIKE_SYMBOL;
	}
	
	/**
	 * 将前台传过来的面数据转换格式
	 * @param str
	 * @return
	 */
	public static String convertPoint(String str){
		String[] strs = str.split(",");
		if (strs.length % 2 == 0) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0, length = strs.length; i < length;) {
				buffer.append(strs[i]);
				buffer.append(" ");
				buffer.append(strs[i + 1]);
				i += 2;
				if (i < length) {
					buffer.append(",");
				}
			}
			return buffer.toString();
		}else{
			throw new RuntimeException("坐标点的格式不对");
		}
	}
	
	public static String encodeMD5(String str){
		try {
			byte[] buf = str.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("md5");
			md5.update(buf);
			byte[] tmp = md5.digest();
			StringBuilder sb = new StringBuilder();
			int i;
			for(byte b:tmp){
				i = b;
				if(i<0){
					i+=256;
				}
				if(i<16){
					sb.append("0");
				}
				sb.append(Integer.toHexString(i));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 
	 * 16进制字节数组转换为16进制字符串
	 * 
	 * @author YanMing 2012-2-13
	 * @param hexBytes
	 * @return
	 */
	public static String byteToHexString(byte[] hexBytes){
		StringBuffer sbf = new StringBuffer();
		for(int i=0;i<hexBytes.length;i++){
			int intVal =  hexBytes[i] & 0xFF;
			String tmpStr = Integer.toHexString(intVal);	
			if(tmpStr.length() < 2){
				sbf.append("0").append(tmpStr.toLowerCase());
			}else{
				sbf.append(tmpStr.toLowerCase());
			}
		}
		return sbf.toString();
	}
	
	/**
	 * <p>通过类似{0}这种标记的形式，确认要插入的位置，
	 * 然后将String... strs中的参数依照顺序替换掉原字符串中的标记。
	 * <p>{}里的数字必须大于等于0，且要依照0,1,2,3...这样的顺序进行标记，不规则数字标记会导致无法替换；
	 * 相同数字标记不限数量，也就是可以将一个字符串同时替换到N个位置。
	 * @author ZhengBaohui
	 * 2011-7-29 上午11:04:17
	 *
	 * @param beReplace
	 * @param strs
	 * @return
	 */
	public static String replaceByMark(String beReplace, String... strs) {
		for(int i=0; i<strs.length; i++){
			if(beReplace.indexOf("{"+i+"}") > -1)
				beReplace = beReplace.replace("{"+i+"}", strs[i]);
		}
		
		return beReplace;
	}
	
	/**
     * 重命名，时间磋
     * @return
     */
    public static String getFileNameNew(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(new Date());
    }
    
    /**
	 * List<String>转成字符串
	 * @param ids
	 * @return 返回 e.g. 1,2,3,4 字符串
	 */
	public static String convertArray4String(String[] ids) {
		StringBuffer sbIds = new StringBuffer("");
		
		if (null != ids && ids.length > 0) {
			for (String id : ids) {
				sbIds.append(id).append(",");
			}
			int index = sbIds.lastIndexOf(",");
			sbIds = sbIds.replace(index, index+1, "");
		}
		
		return sbIds.toString();
	}
	
	/** 
	 * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询 
	 */  
	public static String converToString(String[] ig) {  
	    String str = "";  
	    if (ig != null && ig.length > 0) {  
	        for (int i = 0; i < ig.length; i++) {  
	            str += ig[i] + ",";  
	        }  
	    }  
	    str = str.substring(0, str.length() - 1);  
	    return str;  
	}  
	
	/**
	 *  删除起始字符 
	 */
	public static String trimStart(String str,String trim){
		if(str==null)
			return null;
			return str.replaceAll("^("+trim+")+", "");
	}

		/**
		 * 删除末尾字符 
		 */
	public static String trimEnd(String str,String trim){
		if(str==null)
			return null;
		return str.replaceAll("("+trim+")+$", "");
	}
	
	/**
	 * 处理sql in条件列表大于1000问题
	 * @param list
	 * @return
	 */
	public static <T> List<List<T>> getSumArrayList(List<T> list){
		List<List<T>> objectlist = new ArrayList<List<T>>();
		int iSize = list.size()/1000;
		int iCount = list.size()%1000;
		for(int i=0;i<=iSize;i++){
			List<T> newObjList = new ArrayList<T>();
			if(i==iSize){
				for(int j =i*1000;j<i*1000+iCount;j++){
					newObjList.add(list.get(j));
					}
				}else{
					for(int j =i*1000;j<(i+1)*1000;j++ ){
						newObjList.add(list.get(j));
						}
					}
			if(newObjList.size()>0){
				objectlist.add(newObjList);
				}
			}
		return objectlist;
	}
	public static boolean stringOrNull(String string){
		boolean a = false;
		if (string!=null&&string.trim().length()>0) {
			a =true;
		}
		return a;
	}
	public static void main(String[] args) {
		System.out.println(encodeMD5("hpgayj2017"));
		
	}

	public static boolean stringOrNull(Short bcqx) {
		// TODO Auto-generated method stub
		return false;
	}
}