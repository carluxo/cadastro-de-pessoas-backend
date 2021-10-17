package br.com.carloswayand.pessoas.resources.autenticacao;

import br.com.carloswayand.pessoas.resources.core.ResourceBuild;
import spark.Service;

public class AutenticacaoResourceBuild extends ResourceBuild {
	public AutenticacaoResourceBuild(Service service) {
		super(service, "/api/v1/autenticacoes");
	}

	@Override
	protected void initializeEndpoints() {
		service.post(path, new AutenticacaoPostResource(new BasicAuthenticationService()));
		
		service.exception(AuthenticationException.class, (exception, request, response) -> {
			response.status(400);
			response.body("{\"message\":\"" + exception.getMessage() + "\"}");
		});	
	}
}
