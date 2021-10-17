package br.com.carloswayand.pessoas.resources.pessoa;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.core.data.Repository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.utils.JsonResponseTransformer;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class PessoaPatchResourceTest {

	protected static Repository<Pessoa> repository = new PessoaRepository();
	
	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			http.patch("/api/v1/pessoas/:id", new PessoaPatchResource(repository), new JsonResponseTransformer());
		});
	}
	
	@Test
	void update() throws JsonProcessingException {
		var create = repository.create(new Pessoa("update", LocalDate.of(1970, 12, 31), "020.400.270-25"));
		
		Map<String, Object> map = JsonUtils.fromJsonToMap(JsonUtils.fromObjectToJson(create));
		map.put("nome",  "ALTERANDO O NOME");
		map.put("naturalidade",  "Petrópolis");
		Pessoa fromMap = JsonUtils.fromMapToObject(map, Pessoa.class);		
		
		HttpResponse<String> response = Unirest.spawnInstance().patch(ResourceTest.getPath("/api/v1/pessoas/" + create.getId())).body(JsonUtils.fromObjectToJson(fromMap)).asString();
		Pessoa from = JsonUtils.fromJsonToObject(response.getBody(), Pessoa.class);
		
		assertEquals(fromMap.getId(), from.getId());
		assertEquals(create.getId(), from.getId());
		
		assertEquals(fromMap.getNome(), from.getNome());
		assertNotEquals(create.getNome(), from.getNome());
		
		assertEquals(fromMap.getCreatedAt(), from.getCreatedAt());
		assertEquals(create.getCreatedAt(), from.getCreatedAt());
		
		assertNotNull(from.getUpdatedAt());
	}
	
	@Test
	void naoDeveAtualizarCasoNaoHajaAlteracoes() throws JsonProcessingException {
		var create = repository.create(new Pessoa("naoDeveAtualizarCasoNaoHajaAlteracoes", LocalDate.of(1970, 12, 31), ""));
		HttpResponse<String> response = Unirest.spawnInstance().patch(ResourceTest.getPath("/api/v1/pessoas/" + create.getId())).body(JsonUtils.fromObjectToJson(create)).asString();
		Pessoa from = JsonUtils.fromJsonToObject(response.getBody(), Pessoa.class);
		
		assertEquals(create.getId(), from.getId());
		assertEquals(create.getNome(), from.getNome());		
		assertEquals(create.getCreatedAt(), from.getCreatedAt());		
		assertNull(from.getUpdatedAt());
	}

}
