package br.com.carloswayand.pessoas.resources.autenticacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.domain.Autenticacao;
import br.com.carloswayand.pessoas.resources.pessoa.ResourceTest;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class AutenticacaoPostResourceTest {

	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			http.post("/api/v1/autenticacoes", new AutenticacaoPostResource(new BasicAuthenticationService()));
		});
	}

	@Test
	void deveAutenticarUsuario() {
		Autenticacao autenticacao = new Autenticacao("admin", "admin");

		HttpResponse<String> response = Unirest.spawnInstance().post(ResourceTest.getPath("/api/v1/autenticacoes"))
				.body(JsonUtils.fromObjectToJson(autenticacao)).asString();
		assertEquals(200, response.getStatus());
		assertNotNull(response.getHeaders().get("Authorization"));
		assertNotNull(response.getBody());

	}

}
