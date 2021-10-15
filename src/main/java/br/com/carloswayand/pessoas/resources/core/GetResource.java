package br.com.carloswayand.pessoas.resources.core;

import spark.Request;
import spark.Response;
import spark.Route;

public interface GetResource<T> extends Route {
	@Override
	default T handle(Request request, Response response) throws Exception {
		return this.handleGet(request);
	}

	T handleGet(Request request);
}
