package com.flexi.javaWebFrame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public static <T> String toJson(T obj) {
		String json;
		try {
			json = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("convert pojo to json failure",e);
			throw new RuntimeException(e);
		}
		return json;
	}
	
	public static <T> T fromJson (String json,Class<T> type) {
		T pojo;
		try {
			pojo = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			logger.error("convert json to pojos failure",e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
