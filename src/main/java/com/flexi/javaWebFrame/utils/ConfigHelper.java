package com.flexi.javaWebFrame.utils;

import java.util.Properties;

import com.flexi.javaWebFrame.utils.PropsUtil;

/**
 * 
 * 解析属性文件
 * 
 * @author vixtel
 *
 */
public final class ConfigHelper {
	
	private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
	
	/**
	 * @return jdbcdriver
	 */
	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}
	
	/**
	 * @return url
	 */
	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}
	
	/**
	 * @return username
	 */
	public static String getJdbcUserName() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}
	
	/**
	 * @return password
	 */
	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}
	
	/**
	 * @return appbasepackage
	 */
	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
	}
	
	/**
	 * @return appjsppath
	 */
	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
	}
	
	/**
	 * @return appassetpath
	 */
	public static String getAppAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH,"/asset/");
	}
	
	
}
