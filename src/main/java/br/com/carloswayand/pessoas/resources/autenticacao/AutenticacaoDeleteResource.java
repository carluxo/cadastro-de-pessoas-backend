package br.com.carloswayand.pessoas.resources.autenticacao;

import br.com.carloswayand.pessoas.resources.core.DeleteResource;
import spark.Request;
import spark.Response;

public class AutenticacaoDeleteResource implements DeleteResource {
	protected AuthenticationService service;

	public AutenticacaoDeleteResource(AuthenticationService service) {
		this.service = service;
	}

	@Override
	public Object handleDelete(Request request, Response response) {
		String authorization = request.headers("Authorization");
		this.service.unauthenticate(authorization);
		response.status(204);
		return "";
	}

}
