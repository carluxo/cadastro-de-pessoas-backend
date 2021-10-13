package br.com.carloswayand.pessoas.resources.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.core.data.MemoryRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.JsonUtils;
import br.com.carloswayand.pessoas.resources.core.JsonResponseTransformer;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class PessoaReadAllResourceTest {
	
	protected static IRepository<Pessoa> repository = new MemoryRepository<Pessoa>();
	
	@BeforeAll
	static void beforeAll(SparkStarter s) {
		repository.create(new Pessoa("TESTE UM", LocalDate.MIN));
		repository.create(new Pessoa("TESTE DOIS", LocalDate.MIN));
		repository.create(new Pessoa("TESTE TRES", LocalDate.MIN));
		
		s.runSpark(http -> {
			http.get("/api/v1/pessoas", new PessoaReadAllResource(repository), new JsonResponseTransformer());
		});
	}
	
	@Test
	void deveRetornarAListaDePessoas() throws JsonProcessingException {
		HttpResponse<String> response = Unirest.spawnInstance().get(ResourceTest.getPath("/api/v1/pessoas")).asString();
		List<Pessoa> typeRef = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		List<Pessoa> from = JsonUtils.fromJsonToObject(response.getBody(), typeRef.getClass());
		
		assertNotNull(from);
		assertFalse(from.isEmpty());
		assertEquals(3, from.size());
	}
}
