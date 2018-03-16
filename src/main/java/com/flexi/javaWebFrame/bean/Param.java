package com.flexi.javaWebFrame.bean;

import java.util.Map;

import com.flexi.javaWebFrame.utils.CastUtil;

public class Param {
	
	private Map<String,Object> paramMap;
	
	public Param(Map<String,Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	public long getLong(String name) {
		return CastUtil.castLong(paramMap.get(name));
	}
	
	public Map<String,Object> getMap(){
		return paramMap;
	}

}
