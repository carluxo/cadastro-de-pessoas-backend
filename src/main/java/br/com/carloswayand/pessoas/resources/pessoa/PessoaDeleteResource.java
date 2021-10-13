package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import spark.Request;
import spark.Response;
import spark.Route;

public class PessoaDeleteResource implements Route {

	private IRepository<Pessoa> repository;

	public PessoaDeleteResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String id = request.params("id");
		this.repository.findById(id);
		this.repository.delete(id);
		
		response.status(204);
		return "";
	}

}
