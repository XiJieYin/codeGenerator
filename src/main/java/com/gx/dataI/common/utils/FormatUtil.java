package com.gx.dataI.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormatUtil {
	public static List<Map<String, Object>> convertUp2LowerCase(List<Map<String, Object>> list){
		List<Map<String, Object>> infoList = new ArrayList<Map<String,Object>>();
		if(list==null){
			return null;
		}
		/*默认返回的可能是大写的Key，这个改不了，只能手动转*/
		for (Map<String, Object> map : list) {
			Map<String, Object> info = new HashMap<String, Object>();
			for(String key : map.keySet()){
				Object value = map.get(key);
				info.put(key.toLowerCase(), value);
			}
			infoList.add(info);
		}
		return infoList;
	}
}
