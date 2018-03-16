package com.flexi.javaWebFrame.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

public final class BeanHelper {
	
	/**
	 * 定义bean 的映射
	 */
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
	
	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		
		for (Class<?> beanClass : beanClassSet) {
			Object obj = ReflectionUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, obj);
		}
		
	}
	
	/**
	 * 获取bean实例
	 * @return
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 获取bean实例
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) {
		
		if(!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("cant get bean by class :"+cls);
		}
		
		return (T) BEAN_MAP.get(cls);
		
	}
	
	
	
}
