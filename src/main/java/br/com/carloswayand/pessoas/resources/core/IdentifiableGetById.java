package br.com.carloswayand.pessoas.resources.core;

import br.com.carloswayand.pessoas.core.data.Identifiable;
import spark.Request;

public interface IdentifiableGetById<T extends Identifiable> extends GetResource<T> {
	@Override
	default T handleGet(Request request) {
		String id = request.params("id");
		return this.handleGet(id);
	}

	T handleGet(String id);
}
