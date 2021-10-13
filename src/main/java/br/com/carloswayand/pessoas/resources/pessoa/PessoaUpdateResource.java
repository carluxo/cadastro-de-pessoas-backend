package br.com.carloswayand.pessoas.resources.pessoa;

import java.util.Map;

import br.com.carloswayand.pessoas.core.data.IRepository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.core.JsonUtils;
import spark.Request;
import spark.Response;
import spark.Route;

public class PessoaUpdateResource implements Route {
	private IRepository<Pessoa> repository;

	public PessoaUpdateResource(IRepository<Pessoa> repository) {
		this.repository = repository;
	}

	@Override
	public Pessoa handle(Request request, Response response) throws Exception {
		String id = request.params("id");
		Pessoa persisted = this.repository.findById(id);			
		
		Map<String, Object> toUpdate = JsonUtils.fromJsonToMap(request.body());
		toUpdate.remove("id");
		toUpdate.remove("createdAt");
		toUpdate.remove("updatedAt");		
		
		Map<String, Object> fromDatabase = JsonUtils.fromJsonToMap(JsonUtils.fromObjectToJson(persisted));
		
		boolean hasDifference = false;
		
		for (var e : toUpdate.entrySet()) {
			var key = e.getKey();
			
			if (!fromDatabase.get(key).equals(e.getValue())) {
				fromDatabase.put(key, e.getValue());
				hasDifference = true;
			}
		}
		
		if (hasDifference) {
			Pessoa pessoa = JsonUtils.fromMapToJson(fromDatabase, Pessoa.class);
			persisted = this.repository.update(id, pessoa);			
		}
		
		return persisted;
	}

}
