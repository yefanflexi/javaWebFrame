package com.flexi.javaWebFrame.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vixtel
 *
 */
public final class CodecUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

	public static String encodeURL(String source) {
		String target;
		try {
			target = URLEncoder.encode(source, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("encode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	public static String decodeURL(String source) {
		String target;
		try {
			target = URLDecoder.decode(source, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("encode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
		
	}
}
