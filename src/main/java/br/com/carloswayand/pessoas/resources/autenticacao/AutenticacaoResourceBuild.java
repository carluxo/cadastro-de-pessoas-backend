package br.com.carloswayand.pessoas.resources.autenticacao;

import br.com.carloswayand.pessoas.resources.core.ResourceBuild;
import spark.Service;

public class AutenticacaoResourceBuild extends ResourceBuild {
	public AutenticacaoResourceBuild(Service service) {
		super(service, "/api/v1/autenticacoes");
	}

	@Override
	protected void initializeEndpoints() {
		var authenticationService = new BasicAuthenticationService();
		
		service.post(path, new AutenticacaoPostResource(authenticationService));
		service.delete(path, new AutenticacaoDeleteResource(authenticationService));
	}
}
