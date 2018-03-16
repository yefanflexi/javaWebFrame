package com.flexi.javaWebFrame.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 * @author vixtel
 *
 */
public final class ReflectionUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * 创建实例
	 * @param cls
	 * @return
	 */
	public static Object newInstance(Class<?> cls) {
		Object instance = null;
		try {
			instance = cls.newInstance();
		} catch (Exception e) {
			logger.error("new instance failure",e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	/**
	 * 调用方法
	 * @param obj
	 * @param method
	 * @param args
	 * @return result
	 */
	public static Object invokeMethod(Object obj,Method method,Object...args) {
		Object result = null;
		try {
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			logger.error("invoke method failure",e);
			throw new RuntimeException(e);
		}
		return result;
		
	}
	
	/**
	 * 设置成员变量value
	 * @param obj
	 * @param field
	 * @param value
	 */
	public static void setField(Object obj,Field field,Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			logger.error("ser field failure",e);
			throw new RuntimeException(e);
		}
	}

}
