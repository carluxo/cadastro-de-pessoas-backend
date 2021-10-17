package br.com.carloswayand.pessoas.resources.pessoa;

import java.util.HashMap;
import java.util.Map;

import javax.security.sasl.AuthenticationException;
import javax.validation.ConstraintViolationException;

import br.com.carloswayand.pessoas.core.data.Repository;
import br.com.carloswayand.pessoas.core.data.IdentifiableNotFoundException;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.filters.BasicAuthenticationFilter;
import br.com.carloswayand.pessoas.resources.autenticacao.AuthenticationService;
import br.com.carloswayand.pessoas.resources.autenticacao.BasicAuthenticationService;
import br.com.carloswayand.pessoas.resources.utils.JsonResponseTransformer;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import spark.Service;

public class PessoaResource {
	private static final String APPLICATION_JSON = "application/json";
	
	private PessoaResource() {
		
	}
	
	public static void init(Service service, String path) {
		JsonResponseTransformer transformer = new JsonResponseTransformer();
		Repository<Pessoa> repository = new PessoaRepository();

		service.post(path, new PessoaPostResource(repository), transformer);
		service.get(path, APPLICATION_JSON, new PessoaGetResource(repository), transformer);
		service.get(path + "/:id", APPLICATION_JSON, new PessoaGetByIdResource(repository), transformer);
		service.patch(path + "/:id", APPLICATION_JSON, new PessoaPatchResource(repository), transformer);
		service.delete(path + "/:id", new PessoaDeleteResource(repository));
		
		AuthenticationService authenticationService = new BasicAuthenticationService();
		service.before(path, new BasicAuthenticationFilter(authenticationService));
		
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
	}
}
