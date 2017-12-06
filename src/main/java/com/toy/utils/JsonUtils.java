package com.toy.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json转换工具类
 * 
 * @author 枫茗丿love
 *
 */
public class JsonUtils {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 将对象转化为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) {
		String json = null;
		try {
			json = OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 将json字符串转化为对象
	 * 
	 * @param json
	 * @param beanType
	 * @return
	 */
	public static <T> T jsonToObject(String json, Class<T> beanType) {
		T t = null;
		try {
			t = OBJECT_MAPPER.readValue(json, beanType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将json字符串转化为对象list
	 * 
	 * @param json
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToObjectList(String json, Class<T> beanType) {
		JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		List<T> objectList = null;
		try {
			objectList = OBJECT_MAPPER.readValue(json, javaType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objectList;
	}

}
