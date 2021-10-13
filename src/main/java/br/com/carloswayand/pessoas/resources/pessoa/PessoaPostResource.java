package br.com.carloswayand.pessoas.resources.pessoa;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.JsonUtils;
import spark.Request;
import spark.Response;
import spark.Route;

public class PessoaPostResource implements Route {
	private IRepository<Pessoa> repository;
	
	public PessoaPostResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}
	
	@Override
	public Pessoa handle(Request request, Response response) throws Exception {
		return handlePost(request);
	}

	private Pessoa handlePost(Request request) throws JsonProcessingException {
		var pessoa = JsonUtils.fromJsonToObject(request.body(), Pessoa.class);
		return this.repository.create(pessoa);
	}
	
}
