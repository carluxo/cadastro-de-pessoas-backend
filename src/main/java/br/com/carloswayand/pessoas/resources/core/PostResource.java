package br.com.carloswayand.pessoas.resources.core;

import spark.Request;
import spark.Response;
import spark.Route;

public interface PostResource<T> extends Route {
	@Override
	public default T handle(Request request, Response response) throws Exception {
		return handlePost(request);
	}

	T handlePost(Request request);
}
