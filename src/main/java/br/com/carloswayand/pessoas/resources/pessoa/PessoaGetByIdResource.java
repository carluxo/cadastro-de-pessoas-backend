package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.IdentifiableGetById;

public class PessoaGetByIdResource implements IdentifiableGetById<Pessoa> {
	private IRepository<Pessoa> repository;


	public PessoaGetByIdResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public Pessoa handleGet(String id) {
		return this.repository.findById(id);
	}
}
