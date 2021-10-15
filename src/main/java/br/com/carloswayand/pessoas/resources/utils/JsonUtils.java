package br.com.carloswayand.pessoas.resources.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
	protected static final ObjectMapper MAPPER;

	static {
		MAPPER = new ObjectMapper();
		MAPPER.registerModule(new JavaTimeModule());
		MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		MAPPER.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	}

	private JsonUtils() {

	}

	public static String fromObjectToJson(Object model) {
		try {
			return MAPPER.writeValueAsString(model);
		} catch (Exception e) {
			throw new JsonUtilException(e);
		}
	}

	public static <T> T fromJsonToObject(String json, Class<T> type) {
		try {
			return MAPPER.readValue(json, type);
		} catch (Exception e) {
			throw new JsonUtilException(e);
		}
	}

	public static Map<String, Object> fromJsonToMap(String json) {
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};
		try {
			return MAPPER.readValue(json, typeRef);
		} catch (Exception e) {
			throw new JsonUtilException(e);
		}
	}

	public static <T> T fromMapToObject(Map<String, Object> map, Class<T> type) {
		try {
			String json = fromObjectToJson(map);
			return fromJsonToObject(json, type);
		} catch (Exception e) {
			throw new JsonUtilException(e);
		}
	}
}
