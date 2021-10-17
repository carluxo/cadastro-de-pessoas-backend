package br.com.carloswayand.pessoas.resources.pessoa;

import java.util.concurrent.ConcurrentHashMap;

import br.com.carloswayand.pessoas.core.data.MemoryRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;

public class PessoaRepository extends MemoryRepository<Pessoa> {
	protected ConcurrentHashMap<String, Pessoa> databaseByCpf = new ConcurrentHashMap<>();
	
	@Override
	public Pessoa create(Pessoa obj) {
		Pessoa pessoa = this.databaseByCpf.get(obj.getCpf());
		
		if (pessoa == null) {
			pessoa = super.create(obj);
			this.databaseByCpf.put(pessoa.getCpf(), pessoa);
			return pessoa;
		}
		
		throw new IllegalArgumentException("Já existe um cadastro com o CPF informado");	
	}
	
	@Override
	public Pessoa update(String id, Pessoa obj) {
		Pessoa pessoa = this.databaseByCpf.get(obj.getCpf());
		
		if (pessoa == null || pessoa.getId().equals(obj.getId())) {
			pessoa = super.update(id, obj);
			this.databaseByCpf.put(pessoa.getCpf(), pessoa);
			return pessoa;
		}
		
		throw new IllegalArgumentException("Já existe um cadastro com o CPF informado");
	}
}
