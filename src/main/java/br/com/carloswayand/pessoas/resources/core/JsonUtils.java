package br.com.carloswayand.pessoas.resources.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
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

	public static String fromObjectToJson(Object model) throws JsonProcessingException {
		return MAPPER.writeValueAsString(model);
	}

	public static <T> T fromJsonToObject(String json, Class<T> type) throws JsonProcessingException {
		return MAPPER.readValue(json, type);
	}

	public static Map<String, Object> fromJsonToMap(String json) throws JsonProcessingException {
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};

		return MAPPER.readValue(json, typeRef);
	}
	
	public static <T> T fromMapToJson(Map<String, Object> map, Class<T> type) throws JsonProcessingException {
		String json = fromObjectToJson(map);
		return fromJsonToObject(json, type);
	}
}
