package br.com.carloswayand.pessoas.resources.github;

import static org.junit.Assert.assertNotNull;
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
class GithubGetResourceTest {
	@BeforeAll
	static void beforeAll(SparkStarter s) {
		s.runSpark(http -> {
			new GithubResourceBuild(http).build();
		});
	}

	@Test
	void test() {
		HttpResponse<String> response = Unirest.spawnInstance().get(ResourceTest.getPath("/api/v1/repositories")).asString();
		assertEquals(200, response.getStatus());
		assertNotNull(response.getBody());
	}

}
