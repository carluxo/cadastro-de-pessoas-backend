package br.com.carloswayand.pessoas;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import br.com.carloswayand.pessoas.core.data.IdentifiableNotFoundException;
import br.com.carloswayand.pessoas.resources.autenticacao.AutenticacaoResourceBuild;
import br.com.carloswayand.pessoas.resources.autenticacao.AuthenticationException;
import br.com.carloswayand.pessoas.resources.pessoa.PessoaResourceBuild;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;

public class Api {
	public static void main(String[] args) {
		spark.Service service = spark.Service.ignite();
		service.port(8080);

		service.before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Headers", "*");
			response.header("Access-Control-Allow-Credentials", "true");
			response.header("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PATCH");
		});

		service.options("/*", (request, response) -> {
			return "OK";
		});

		service.exception(IdentifiableNotFoundException.class, (exception, request, response) -> {
			response.status(404);
		});
		
		service.exception(AuthenticationException.class, (exception, request, response) -> {
			response.status(401);
			response.body("{\"message\":\"" + exception.getMessage() + "\"}");
		});

		service.exception(IllegalArgumentException.class, (exception, request, response) -> {
			response.status(400);
			response.body("{\"message\":\"" + exception.getMessage() + "\"}");
		});

		service.exception(ConstraintViolationException.class, (exception, request, response) -> {
			response.status(422);

			Map<String, String> messages = new HashMap<>();

			exception.getConstraintViolations().forEach(error -> {
				messages.put(error.getPropertyPath().toString(), error.getMessage());
			});

			response.body("{\"violations\": " + JsonUtils.fromObjectToJson(messages) + "}");
		});

		new PessoaResourceBuild(service).build();
		new AutenticacaoResourceBuild(service).build();

		service.init();
	}
}
