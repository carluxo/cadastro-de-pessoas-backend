package br.com.carloswayand.pessoas.resources.github;

import br.com.carloswayand.pessoas.resources.core.ResourceBuild;
import spark.Service;

public class GithubResourceBuild extends ResourceBuild {

	public GithubResourceBuild(Service service) {
		super(service, "/api/v1/repositories");
	}
	
	@Override
	protected void initializeEndpoints() {
		this.service.get(path, new GithubGetResource());
	}

}
