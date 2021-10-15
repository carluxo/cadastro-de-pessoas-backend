package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.IdentifiableDeleteResource;

public class PessoaDeleteResource implements IdentifiableDeleteResource {

	private IRepository<Pessoa> repository;

	public PessoaDeleteResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public void handleDelete(String id) {
		this.repository.findById(id);
		this.repository.delete(id);		
	}

}
