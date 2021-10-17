package br.com.carloswayand.pessoas.resources.pessoa;

import java.util.Map;

import br.com.carloswayand.pessoas.core.data.Repository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.IdentifiablePatchResource;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;

public class PessoaPatchResource implements IdentifiablePatchResource<Pessoa> {
	private Repository<Pessoa> repository;

	public PessoaPatchResource(Repository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public Pessoa handlePatch(String id, Map<String, Object> map) {
		Pessoa persisted = this.repository.findById(id);
		Map<String, Object> fromDatabase = JsonUtils.fromJsonToMap(JsonUtils.fromObjectToJson(persisted));

		boolean hasDifference = false;

		for (var e : map.entrySet()) {
			var key = e.getKey();

			if (fromDatabase.get(key) != null) {
				if (!fromDatabase.get(key).equals(e.getValue())) {
					fromDatabase.put(key, e.getValue());
					hasDifference = true;
				}
			} else if (e.getValue() != null) {
				fromDatabase.put(key, e.getValue());
				hasDifference = true;
			}
		}

		if (hasDifference) {
			Pessoa pessoa = JsonUtils.fromMapToObject(fromDatabase, Pessoa.class);
			persisted = this.repository.update(id, pessoa);
		}

		return persisted;
	}

}
