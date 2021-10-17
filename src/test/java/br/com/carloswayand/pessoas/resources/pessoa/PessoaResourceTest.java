package br.com.carloswayand.pessoas.resources.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.resources.autenticacao.BasicAuthenticationService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class PessoaResourceTest {
	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			new PessoaResourceBuild(http).build();
		});
	}

	@Test
	void deveCarregarOsServicosDePessoa() {
		var service = new BasicAuthenticationService();
		String token = service.authenticate("admin:admin");
		HttpResponse<String> response = Unirest.spawnInstance().get(ResourceTest.getPath("/api/v1/pessoas"))
				.header("Authorization", "Basic " + token).asString();
		assertEquals(200, response.getStatus());
		assertNotNull(response.getBody());
	}

}
