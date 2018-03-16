package com.flexi.javaWebFrame.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.flexi.javaWebFrame.annotation.Action;
import com.flexi.javaWebFrame.bean.Handler;
import com.flexi.javaWebFrame.bean.Request;

public final class ControllerHelper {

	private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();
	
	static {
		Set<Class<?>> controllerClassSet = ClassHelper.getClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)) {
			for (Class<?> controllerClass : controllerClassSet) {
				//获取Controller定义的方法
				Method[] methods = controllerClass.getDeclaredMethods();
				if(ArrayUtils.isNotEmpty(methods)) {
					for (Method method : methods) {
						if(method.isAnnotationPresent(Action.class)) {
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							//验证url映射规则
							if(mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if(ArrayUtils.isNotEmpty(array)&&array.length == 2) {
									//获取请求方法和路径
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									//初始化Action map
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
				
			}
		}
	}
	public static Handler getHandler(String requestMethod,String requestPath) {
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
		
	}
}
