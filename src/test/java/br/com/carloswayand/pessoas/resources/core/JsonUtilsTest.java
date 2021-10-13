package br.com.carloswayand.pessoas.resources.core;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

class JsonUtilsTest {
	protected static JsonObjectTest created;
	protected static Instant date = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.UTC);
	
	
	@BeforeEach
	void beforeEach() {
		created = new JsonObjectTest();
	}
	
	@Test
	void deveTransformarEmStringJson() throws JsonProcessingException {
		String json = JsonUtils.fromObjectToJson(created);
		
		assertNotNull(json);
		assertTrue(json.contains("TESTE"));
		assertTrue(json.contains(date.toString()));
	}
	
	@Test
	void deveTransformarEmObjeto() throws JsonProcessingException {
		String json = JsonUtils.fromObjectToJson(created);
		var fromJsonToObject = JsonUtils.fromJsonToObject(json, JsonObjectTest.class);
		
		assertNotNull(fromJsonToObject);
		assertEquals("TESTE", fromJsonToObject.nome);
		assertEquals(date, fromJsonToObject.data);
	}
	
	@Test
	void deveTransformarEmHashMap() throws JsonProcessingException {
		Map<String, Object> map = JsonUtils.fromJsonToMap(JsonUtils.fromObjectToJson(created));
		
		assertNotNull(map);
		assertFalse(map.isEmpty());
		assertEquals(map.get("nome"), created.nome);
		assertEquals(map.get("data") , created.data.toString());
	}
	
	@Test
	void deveTransformarEmObjetoDeUmHashMap() throws JsonProcessingException {
		Map<String, Object> map = JsonUtils.fromJsonToMap(JsonUtils.fromObjectToJson(created));
		var jsonFromMap = JsonUtils.fromMapToJson(map, JsonObjectTest.class);
		
		assertNotNull(jsonFromMap);
		assertEquals("TESTE", jsonFromMap.nome);
		assertEquals(date , jsonFromMap.data);
	}
}

class JsonObjectTest {
	String nome = "TESTE";
	Instant data =JsonUtilsTest.date;
	
	public JsonObjectTest() {
		assertNotNull(this);
	}
	
}
