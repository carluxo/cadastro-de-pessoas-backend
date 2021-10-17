package br.com.carloswayand.pessoas.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.carloswayand.pessoas.JavaSparkRunnerExtension;
import br.com.carloswayand.pessoas.JavaSparkRunnerExtension.SparkStarter;
import br.com.carloswayand.pessoas.resources.autenticacao.AuthenticationException;
import br.com.carloswayand.pessoas.resources.autenticacao.BasicAuthenticationService;
import br.com.carloswayand.pessoas.resources.pessoa.ResourceTest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

@ExtendWith({ ResourceTest.class, JavaSparkRunnerExtension.class })
class BasicAuthenticationFilterTest {
	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			http.get("/", (req, resp) -> "Teste");
			http.before("/", new BasicAuthenticationFilter(new BasicAuthenticationService()));
			http.exception(AuthenticationException.class, (ex, req, resp) -> {
				resp.status(401);
				resp.body(ex.getMessage());
			});
		});
	}

	@Test
	void naoDeveAcessarSemToken() throws UnirestException, JsonProcessingException {
		HttpResponse<String> response = Unirest.spawnInstance().get("http://localhost:8080/").asString();
		assertEquals(401, response.getStatus());
	}

	@Test
	void naoDeveAcessarComTokenForjado() throws UnirestException, JsonProcessingException {
		HttpResponse<String> response = Unirest.spawnInstance().get("http://localhost:8080/")
				.header("Authorization", "Basic JKAHSJHAJHSJKAHSJHJHDJ,mbnmbnmabsdnabsd").asString();
		assertEquals(401, response.getStatus());
	}
	
	@Test
	void naoDeveAcessarComTokenVazio() throws UnirestException, JsonProcessingException {
		HttpResponse<String> response = Unirest.spawnInstance().get("http://localhost:8080/")
				.header("Authorization", "").asString();
		assertEquals(401, response.getStatus());
	}
	
	@Test
	void deveAcessarComTokenValido() throws UnirestException, JsonProcessingException {
		String token = new BasicAuthenticationService().authenticate("admin:admin");
		HttpResponse<String> response = Unirest.spawnInstance().get("http://localhost:8080/")
				.header("Authorization", "Basic " + token).asString();
		assertEquals(200, response.getStatus());
		assertEquals("Teste", response.getBody());
	}

}
