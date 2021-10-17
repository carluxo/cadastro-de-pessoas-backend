package br.com.carloswayand.pessoas.resources.core;

import br.com.carloswayand.pessoas.resources.utils.JsonResponseTransformer;
import spark.Service;

public abstract class ResourceBuild {
	protected static final String APPLICATION_JSON = "application/json";
	protected static final JsonResponseTransformer transform = new JsonResponseTransformer();
	protected String path;
	protected Service service;
	
	protected ResourceBuild(Service service, String path) {
		this.service = service;
		this.path = path;
	}

	public void build() {
		this.initializeBasicOperations();
		this.initializeEndpoints();
	}

	protected void initializeBasicOperations() {
		service.options(this.path, (request, response) -> {
			response.status(200);
			return "";
		});
	}

	protected abstract void initializeEndpoints();
}
