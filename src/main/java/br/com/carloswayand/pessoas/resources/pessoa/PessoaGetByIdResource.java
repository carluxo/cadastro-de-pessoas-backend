package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.Repository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.IdentifiableGetById;

public class PessoaGetByIdResource implements IdentifiableGetById<Pessoa> {
	private Repository<Pessoa> repository;


	public PessoaGetByIdResource(Repository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public Pessoa handleGet(String id) {
		return this.repository.findById(id);
	}
}
