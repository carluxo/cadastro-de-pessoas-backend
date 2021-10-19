package br.com.carloswayand.pessoas.resources.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.carloswayand.pessoas.domain.Pessoa;

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
		var jsonFromMap = JsonUtils.fromMapToObject(map, JsonObjectTest.class);
		
		assertNotNull(jsonFromMap);
		assertEquals("TESTE", jsonFromMap.nome);
		assertEquals(date , jsonFromMap.data);
	}
	
	@Test
	void deveLancarExcecaoEmCasoDeJsonInvalido() {
		assertThrows(JsonUtilException.class, () ->  JsonUtils.fromJsonToMap("{\"teste\":}"));
		assertThrows(JsonUtilException.class, () ->  JsonUtils.fromJsonToObject("{\"coisa\"}", Pessoa.class));
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("coisa", "");		
		assertThrows(JsonUtilException.class, () ->  JsonUtils.fromMapToObject(map, Pessoa.class));
	}
	
}

class JsonObjectTest {
	String nome = "TESTE";
	Instant data =JsonUtilsTest.date;
	
	public JsonObjectTest() {
		assertNotNull(this);
	}
	
}
