package br.com.carloswayand.pessoas.resources.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.core.data.MemoryRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class PessoaDeleteResourceTest {
	protected static IRepository<Pessoa> repository = new MemoryRepository<Pessoa>();

	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			http.delete("/api/v1/pessoas/:id", new PessoaDeleteResource(repository));
		});
	}

	@Test
	void remove() {
		var create = repository.create(new Pessoa("update", LocalDate.of(1970, 12, 31)));
		HttpResponse<String> response = Unirest.spawnInstance()
				.delete(ResourceTest.getPath("/api/v1/pessoas/" + create.getId())).asString();
		assertEquals(204, response.getStatus());
	}
}
