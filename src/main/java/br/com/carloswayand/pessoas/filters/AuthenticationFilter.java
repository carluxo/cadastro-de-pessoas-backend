package br.com.carloswayand.pessoas.filters;

import br.com.carloswayand.pessoas.resources.autenticacao.AuthenticationService;
import spark.Filter;
import spark.Request;
import spark.Response;

public abstract class AuthenticationFilter implements Filter {
	protected AuthenticationService service;

	protected AuthenticationFilter(AuthenticationService service) {
		this.service = service;
	}
	
	@Override
	public void handle(Request request, Response response) throws Exception {
		this.handleAuthentication(request, response);
	}

	protected abstract void handleAuthentication(Request request, Response response);

}
