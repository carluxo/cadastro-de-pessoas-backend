package br.com.carloswayand.pessoas.resources.pessoa;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;

import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;

class PessoaRepositoryTest {

	@Test
	void naoDeveCriarPessoasComMesmoCpf() {
		var repository = new PessoaRepository();
		
		Pessoa pessoa = new Pessoa("Nome", LocalDate.now().minusYears(20), "975.022.350-00");
		repository.create(pessoa);
		
		assertThrows(IllegalArgumentException.class, () -> repository.create(pessoa));
	}
	
	@Test
	void naoDeveAtualizarPessoasComMesmoCpf() {
		var repository = new PessoaRepository();
		
		Pessoa pessoa = new Pessoa("Nome", LocalDate.now().minusYears(20), "975.022.350-00");
		repository.create(pessoa);
		var outraPessoa = new Pessoa("Nome", LocalDate.now().minusYears(20), "298.216.480-96");
		
		Pessoa create = repository.create(outraPessoa);
		
		String json = JsonUtils.fromObjectToJson(create);
		Map<String, Object> map = JsonUtils.fromJsonToMap(json);
		map.put("cpf", pessoa.getCpf());
		Pessoa outraPessoaAlterada = JsonUtils.fromMapToObject(map,Pessoa.class);
		var id = outraPessoaAlterada.getId();
		
		assertThrows(IllegalArgumentException.class, () -> repository.update(id, outraPessoaAlterada));
	}

}
