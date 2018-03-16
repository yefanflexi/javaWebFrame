package com.flexi.javaWebFrame.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 流操作工具
 * @author vixtel
 *
 */
public final class StreamUtil {

	private static Logger logger = LoggerFactory.getLogger(StreamUtil.class);
	
	public static String getString (InputStream is) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = reader.readLine())!=null) {
				sb.append(line);
			}
			
		} catch (Exception e) {
			logger.error("get String failure",e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
}
