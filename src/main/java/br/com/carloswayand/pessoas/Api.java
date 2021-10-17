package br.com.carloswayand.pessoas;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import br.com.carloswayand.pessoas.core.data.IdentifiableNotFoundException;
import br.com.carloswayand.pessoas.resources.pessoa.PessoaResource;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;

public class Api {
	public static void main(String[] args) {
		spark.Service service = spark.Service.ignite();
		service.port(8080);
		
		service.before((request, response) -> {
			response.header("Access-Control-Allow-Methods", "GET,PATCH,POST,DELETE,OPTIONS");
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
			response.header("Access-Control-Allow-Credentials", "true");
		});
		
		service.options("/api/v1/*", (request, response) -> {
			response.status(200);
			return "";
		});
		
		service.exception(IdentifiableNotFoundException.class, (exception, request, response) -> {
			response.status(404);
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
		
		PessoaResource.init(service, "/api/v1/pessoas");
		
		service.init();
	}
}
