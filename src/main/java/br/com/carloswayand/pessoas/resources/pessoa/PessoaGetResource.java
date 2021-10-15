package br.com.carloswayand.pessoas.resources.pessoa;

import java.util.List;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.GetListResource;
import spark.Request;
import spark.Response;

public class PessoaGetResource implements GetListResource<Pessoa> {
	private IRepository<Pessoa> repository;

	public PessoaGetResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public List<Pessoa> handleGet(Request request, Response response) {
		return this.repository.findAll();
	}
}
