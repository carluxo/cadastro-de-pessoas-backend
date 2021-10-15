package br.com.carloswayand.pessoas.resources.core;

import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;

public interface GetListResource<T> extends Route {
	@Override
	default List<T> handle(Request request, Response response) throws Exception {
		return this.handleGet(request, response);
	}

	 List<T> handleGet(Request request, Response response);
}
