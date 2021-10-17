package br.com.carloswayand.pessoas.resources.autenticacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.resources.pessoa.ResourceTest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class AutenticacaoDeleteResourceTest {
	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			http.delete("/api/v1/autenticacoes", new AutenticacaoDeleteResource(new BasicAuthenticationService()));
		});
	}
	
	@Test
	void deveCarregarOsServicosDeAutenticacao() {
		var service = new BasicAuthenticationService();
		var token = service.authenticate("admin:admin");

		HttpResponse<String> response = Unirest.spawnInstance().delete(ResourceTest.getPath("/api/v1/autenticacoes"))
				.header("Authorization", "Basic " + token).asString();
		assertEquals(204, response.getStatus());

	}
}
