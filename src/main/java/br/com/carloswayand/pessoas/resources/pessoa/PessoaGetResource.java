package br.com.carloswayand.pessoas.resources.pessoa;

import java.util.List;

import br.com.carloswayand.pessoas.core.data.Repository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.GetListResource;
import spark.Request;
import spark.Response;

public class PessoaGetResource implements GetListResource<Pessoa> {
	private Repository<Pessoa> repository;

	public PessoaGetResource(Repository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public List<Pessoa> handleGet(Request request, Response response) {
		return this.repository.findAll();
	}
}
