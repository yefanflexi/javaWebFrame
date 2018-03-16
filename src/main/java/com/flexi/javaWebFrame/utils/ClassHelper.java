package com.flexi.javaWebFrame.utils;

import java.util.HashSet;
import java.util.Set;

import com.flexi.javaWebFrame.annotation.Controller;
import com.flexi.javaWebFrame.annotation.Service;

/**
 * 类操作助手
 * @author vixtel
 *
 */
public final class ClassHelper {

	/**
	 * 类集合（用于存放加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * 获取所有的类
	 * @return
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**
	 * 获取所有的Service类
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if(cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取所有的Controller类
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if(cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取所有bean
	 * @return
	 */
	public static  Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
}
