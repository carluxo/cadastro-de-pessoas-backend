package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import spark.Request;
import spark.Response;
import spark.Route;

public class PessoaReadByIdResource implements Route {
	private IRepository<Pessoa> repository;


	public PessoaReadByIdResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}
	
	
	@Override
	public Pessoa handle(Request request, Response response) throws Exception {
		String id = request.params("id");
		return this.repository.findById(id);
	}

}
