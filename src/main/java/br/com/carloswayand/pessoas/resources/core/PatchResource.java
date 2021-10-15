package br.com.carloswayand.pessoas.resources.core;

import spark.Request;
import spark.Response;
import spark.Route;

public interface PatchResource<T> extends Route {
	@Override
	default T handle(Request request, Response response) throws Exception {
		return this.handlePatch(request);
	}

	T handlePatch(Request request);
}
