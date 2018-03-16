package com.flexi.javaWebFrame.utils;



/**
 * 	转换类型操作类
 * @author vixtel
 *
 */
public class CastUtil {
	
	public static String castString(Object obj) {
		return CastUtil.castString(obj, "");
	}
	
	/**
	 * @param obj
	 * @param defaultValue
	 * @return 转化String
	 */
	public static String castString (Object obj,String defaultValue) {
		return obj != null?String.valueOf(obj) : defaultValue;
	}
	
	public static double castDouble(Object obj) {
		return CastUtil.castDouble(obj,0.0);
	}
	
	public static double castDouble(Object obj,Double defaultValue) {
		double doublevalue = defaultValue;
		if(obj != null) {
			String strValue = castString(obj);
			if(StringUtils.isNotEmpty(strValue)) {
				doublevalue = Double.parseDouble(strValue);
			}
		}
		return doublevalue;
	}
	
	public static int castInt(Object obj) {
		return CastUtil.castInt(obj,0);
	}
	
	public static int castInt(Object obj,int defaultValue) {
		int intvalue = defaultValue;
		if(obj != null) {
			String strValue = castString(obj);
			if(StringUtils.isNotEmpty(strValue)) {
				intvalue = Integer.parseInt(strValue);
			}
		}
		return intvalue;
	}
	
	public static boolean castBoolean(Object obj) {
		return CastUtil.castBoolean(obj,false);
	}
	
	public static boolean castBoolean(Object obj,boolean defaultValue) {
		boolean booleanvalue = defaultValue;
		if(obj != null) {
			String strValue = castString(obj);
			if(StringUtils.isNotEmpty(strValue)) {
				booleanvalue = Boolean.parseBoolean(strValue);
			}
		}
		return booleanvalue;
	}

	public static long castLong(Object object) {
		// TODO Auto-generated method stub
		return CastUtil.castLong(object, 0L);
	}
	
	public static long castLong(Object object,Long defaultValue) {
		Long longvalue = defaultValue;
		if(object != null) {
			String strValue = castString(object);
			if(StringUtils.isNotEmpty(strValue)) {
				longvalue = Long.parseLong(strValue);
			}
		}
		return longvalue;
	}
	
	

	
}
