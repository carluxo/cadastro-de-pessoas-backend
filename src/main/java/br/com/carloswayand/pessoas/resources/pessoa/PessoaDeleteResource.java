package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.Repository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.IdentifiableDeleteResource;

public class PessoaDeleteResource implements IdentifiableDeleteResource {

	private Repository<Pessoa> repository;

	public PessoaDeleteResource(Repository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public void handleDelete(String id) {
		this.repository.findById(id);
		this.repository.delete(id);		
	}

}
