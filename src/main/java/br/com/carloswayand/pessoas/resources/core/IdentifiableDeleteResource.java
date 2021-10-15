package br.com.carloswayand.pessoas.resources.core;

import spark.Request;
import spark.Response;

public interface IdentifiableDeleteResource extends DeleteResource {
	default Object handleDelete(Request request, Response response) {
		String id = request.params("id");
		
		this.handleDelete(id);
		
		response.status(204);
		return "";
	}

	void handleDelete(String id);
}
