package com.flexi.javaWebFrame.bean;

import java.lang.reflect.Method;

/**
 * @author vixtel
 *
 */
public class Handler {
	
	private Class<?> controllerClass;
	private Method actionMethod;
	public Class<?> getControllerClass() {
		return controllerClass;
	}
	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}
	public Method getActionMethod() {
		return actionMethod;
	}
	public void setActionMethod(Method actionMethod) {
		this.actionMethod = actionMethod;
	}
	public Handler(Class<?> controllerClass, Method actionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}
	
	

}
