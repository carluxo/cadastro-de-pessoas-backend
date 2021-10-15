package br.com.carloswayand.pessoas.resources.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.core.data.MemoryRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class PessoaResourceTest {
	protected static IRepository<Pessoa> repository = new MemoryRepository<Pessoa>();

	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			PessoaResource.init(http, "/api/v1/pessoas");
		});
	}

	@Test
	void deveReagirAExcecaoIdentifiableNotFound() {
		HttpResponse<String> response = Unirest.spawnInstance().get("http://localhost:8080/api/v1/pessoas/1").asString();
		assertEquals(404, response.getStatus());		
	}
	
	@Test
	void deveReagirAExcecaoConstraintViolationException() throws JsonProcessingException {
		String pessoa = JsonUtils.fromObjectToJson(new Pessoa("", null, ""));
		HttpResponse<String> response = Unirest.spawnInstance().post("http://localhost:8080/api/v1/pessoas").body(pessoa).asString();
		assertEquals(400, response.getStatus());
	}
	

}
