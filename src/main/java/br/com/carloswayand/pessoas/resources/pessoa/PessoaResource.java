package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.core.data.IdentifiableNotFoundException;
import br.com.carloswayand.pessoas.core.data.MemoryRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.JsonResponseTransformer;
import spark.Spark;

public class PessoaResource {
	private static final String APPLICATION_JSON = "application/json";

	protected PessoaResource() {

	}

	public static void init(String path) {
		JsonResponseTransformer transformer = new JsonResponseTransformer();
		IRepository<Pessoa> repository = new MemoryRepository<>();

		Spark.post(path, new PessoaPostResource(repository), transformer);
		Spark.get(path, APPLICATION_JSON, new PessoaReadAllResource(repository), transformer);
		Spark.get(path + "/:id", APPLICATION_JSON, new PessoaReadByIdResource(repository), transformer);
		Spark.patch(path + "/:id", APPLICATION_JSON, new PessoaUpdateResource(repository), transformer);
		Spark.delete(path + "/:id", new PessoaDeleteResource(repository));
		
		Spark.exception(IdentifiableNotFoundException.class, (exception, request, response) -> {
			response.status(404);
		});
	}
}
