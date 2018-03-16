package com.flexi.javaWebFrame.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * 解析属性文件
 * @author vixtel
 *
 */
public final class PropsUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PropsUtil.class);
	
	/**
	 * 加载属性文件
	 * @param filename
	 * @return 
	 * 
	 */
	public static Properties loadProps(String filename) {
		
		Properties props = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
			if(is == null) {
				throw new FileNotFoundException(filename+"file is not found");
			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			logger.error("load properties file failure",e);
		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("close input stream failure",e);
				}
			}
		}
		return props;
	}
	
	/**
	 * @param props
	 * @param key
	 * @return 获取字符属性（默认为空String）
	 */
	public static String getString (Properties props,String key) {
		return getString (props,key,"");
	}
	
	/**
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return 获取字符属性
	 */
	public static String getString (Properties props,String key,String defaultValue) {
		String value = defaultValue;
		if(props.contains(key)) {
			value = props.getProperty(key);
			
		}
		return value;
	}
	
	/**
	 * @param props
	 * @param key
	 * @return 获取字符属性（默认为0）
	 */
	public static int getInt (Properties props,String key) {
		return getInt (props,key,0);
	}
	
	/**
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return 获取字符属性
	 */
	public static int getInt (Properties props,String key,int defaultValue) {
		int value = defaultValue;
		if(props.contains(key)) {
			value = CastUtil.castInt(props.getProperty(key));
			
		}
		return value;
	}
	
	/**
	 * @param props
	 * @param key
	 * @return 获取字符属性（默认为false）
	 */
	public static boolean getBoolean (Properties props,String key) {
		return getBoolean (props,key,false);
	}
	
	/**
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return 获取字符属性
	 */
	public static boolean getBoolean (Properties props,String key,boolean defaultValue) {
		boolean value = defaultValue;
		if(props.contains(key)) {
			value = CastUtil.castBoolean(props.getProperty(key));
			
		}
		return value;
	}
	
	
	
}

