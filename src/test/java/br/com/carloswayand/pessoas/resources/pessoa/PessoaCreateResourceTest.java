package br.com.carloswayand.pessoas.resources.pessoa;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.core.data.MemoryRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.JsonUtils;
import br.com.carloswayand.pessoas.resources.core.JsonResponseTransformer;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class PessoaCreateResourceTest {

	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			http.post("/api/v1/pessoas", new PessoaPostResource(new MemoryRepository<Pessoa>()), new JsonResponseTransformer());
		});
	}

	@Test
	void deveCriarNovaPessoa() throws UnirestException, JsonProcessingException {
		var pessoa = new Pessoa("Um Dois Tres de Oliveira Quatro", LocalDate.of(1970, 1, 2));
		
		HttpResponse<String> response = Unirest.spawnInstance().post(ResourceTest.getPath("/api/v1/pessoas")).body(JsonUtils.fromObjectToJson(pessoa)).asString();
		assertEquals(200, response.getStatus());
		assertNotNull(response.getBody());
		
		Pessoa from = JsonUtils.fromJsonToObject(response.getBody(), Pessoa.class);
		
		assertNotNull(from.getId());
		assertEquals(pessoa.getNome(), from.getNome());
		assertEquals(pessoa.getNascimento(), from.getNascimento());
		assertEquals(LocalDate.now(ZoneId.of("UTC")), LocalDate.ofInstant(from.getCreatedAt(), ZoneId.of("UTC")));
		
	}

}
