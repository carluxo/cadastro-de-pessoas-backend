package br.com.carloswayand.pessoas.resources.core;

import spark.Request;
import spark.Response;
import spark.Route;

public interface DeleteResource extends Route {
	@Override
	default Object handle(Request request, Response response) throws Exception {
		return this.handleDelete(request, response);
	}

	Object handleDelete(Request request, Response response);
}