package br.com.carloswayand.pessoas.resources.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.core.data.MemoryRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.utils.JsonResponseTransformer;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class PessoaGetByIdResourceTest {
	protected static MemoryRepository<Pessoa> repository;
	
	@BeforeAll
	static void beforeAll(SparkStarter s) {
		repository = new MemoryRepository<>();
		
		s.runSpark(http -> {
			http.get("/api/v1/pessoas/:id", new PessoaGetByIdResource(repository), new JsonResponseTransformer());
		});
	}
	
	@Test
	void findById() throws JsonProcessingException {		
		Pessoa created = repository.create(new Pessoa("findById", LocalDate.of(2021, 10, 10), "020.400.270-25"));
		
		HttpResponse<String> response = Unirest.spawnInstance().get(ResourceTest.getPath("/api/v1/pessoas/" + created.getId())).asString();
		Pessoa finded = JsonUtils.fromJsonToObject(response.getBody(), Pessoa.class);
		
		assertNotNull(finded);
		assertEquals(created.getId(), finded.getId());
		assertEquals(created.getNome(), finded.getNome());
		assertEquals(created.getNascimento(), finded.getNascimento());
		assertEquals(created.getCreatedAt(), finded.getCreatedAt());
		
	}

}
