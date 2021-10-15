package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.PostResource;
import br.com.carloswayand.pessoas.resources.utils.BeanValidator;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import spark.Request;

public class PessoaPostResource implements PostResource<Pessoa> {
	private IRepository<Pessoa> repository;

	public PessoaPostResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public Pessoa handlePost(Request request) {
		Pessoa pessoa = JsonUtils.fromJsonToObject(request.body(), Pessoa.class);
		BeanValidator.validate(pessoa);
		
		return this.repository.create(pessoa);
	}

}
