package br.com.carloswayand.pessoas.resources.pessoa;

import java.util.List;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import spark.Request;
import spark.Response;
import spark.Route;

public class PessoaReadAllResource implements Route {
	private IRepository<Pessoa> repository;

	public PessoaReadAllResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public List<Pessoa> handle(Request request, Response response) throws Exception {
		return this.repository.findAll();
	}
}
