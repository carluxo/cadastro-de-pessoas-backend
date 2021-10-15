package br.com.carloswayand.pessoas.resources.core;

import java.util.Map;

import br.com.carloswayand.pessoas.core.data.Identifiable;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import spark.Request;

public interface IdentifiablePatchResource<T extends Identifiable> extends PatchResource<T> {
	@Override
	default T handlePatch(Request request) {
		String id = request.params("id");
		
		Map<String, Object> toUpdate = JsonUtils.fromJsonToMap(request.body());
		toUpdate.remove("id");
		toUpdate.remove("createdAt");
		toUpdate.remove("updatedAt");
		
		return this.handlePatch(id, toUpdate);
	}

	T handlePatch(String id, Map<String, Object> map);
}
