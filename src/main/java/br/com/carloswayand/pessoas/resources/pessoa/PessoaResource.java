package br.com.carloswayand.pessoas.resources.pessoa;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.core.data.IdentifiableNotFoundException;
import br.com.carloswayand.pessoas.core.data.MemoryRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.utils.JsonResponseTransformer;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import spark.Service;

public class PessoaResource {
	private static final String APPLICATION_JSON = "application/json";
	
	private PessoaResource() {
		
	}
	
	public static void init(Service service, String path) {
		JsonResponseTransformer transformer = new JsonResponseTransformer();
		IRepository<Pessoa> repository = new MemoryRepository<>();

		service.post(path, new PessoaPostResource(repository), transformer);
		service.get(path, APPLICATION_JSON, new PessoaGetResource(repository), transformer);
		service.get(path + "/:id", APPLICATION_JSON, new PessoaGetByIdResource(repository), transformer);
		service.patch(path + "/:id", APPLICATION_JSON, new PessoaPatchResource(repository), transformer);
		service.delete(path + "/:id", new PessoaDeleteResource(repository));

		service.exception(IdentifiableNotFoundException.class, (exception, request, response) -> {
			response.status(404);
		});

		service.exception(ConstraintViolationException.class, (exception, request, response) -> {
			response.status(400);

			Map<String, String> messages = new HashMap<>();

			exception.getConstraintViolations().forEach(error -> {
				messages.put(error.getPropertyPath().toString(), error.getMessage());
			});

			response.body(JsonUtils.fromObjectToJson(messages));

		});
	}
}
