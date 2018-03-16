package com.flexi.javaWebFrame.utils;



public final class StringUtils {
	
	public static boolean isEmpty(String str) {
		if(str != null) {
			str=str.trim();
		}
		return org.apache.commons.lang3.StringUtils.isEmpty(str);
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

}
