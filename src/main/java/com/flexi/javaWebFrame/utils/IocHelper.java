package com.flexi.javaWebFrame.utils;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.flexi.javaWebFrame.annotation.Inject;

/**
 * Ioc 依赖注入助手类
 * @author vixtel
 *
 */
public class IocHelper {

	static {
		//获取所有的bean类和映射关系bean map
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)) {
			//遍历 beanmap
			for(Map.Entry<Class<?>, Object>beanEntry:beanMap.entrySet()) {
				//从beanMap获取bean和bean的实例
				Class<?> beanClass = beanEntry.getClass();
				Object beanInstance = beanEntry.getValue();
				//获取bean定义的所有成员变量 bean field
				Field[] beanFields = beanClass.getDeclaredFields();
				if(!ArrayUtils.isEmpty(beanFields)) {
					//遍历bean field
					for (Field beanField : beanFields) {
						//判断当前bean field
						if(beanField.isAnnotationPresent(Inject.class)) {
							//在bean map获取对应实例
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if(null != beanFieldInstance) {
								//通过反射初始化beanField的值
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
				
			}
		}
	}
}
