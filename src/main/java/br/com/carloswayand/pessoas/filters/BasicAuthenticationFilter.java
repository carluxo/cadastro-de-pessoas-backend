package br.com.carloswayand.pessoas.filters;

import br.com.carloswayand.pessoas.resources.autenticacao.AuthenticationException;
import br.com.carloswayand.pessoas.resources.autenticacao.AuthenticationService;
import spark.Request;
import spark.Response;

public class BasicAuthenticationFilter extends AuthenticationFilter {
	public BasicAuthenticationFilter(AuthenticationService service) {
		super(service);
	}

	@Override
	protected void handleAuthentication(Request request, Response response) {
		try {
			if (request.requestMethod().equalsIgnoreCase("options")) {
				response.header("Content-Type", "application/json");
			} else {
				String auth = request.headers("Authorization");

				if (auth.isEmpty()) {
					throw new AuthenticationException("Credenciais inválidas");
				}

				service.verify(auth);
			}
		} catch (Exception e) {
			throw new AuthenticationException("Credenciais inválidas");
		}
	}
}
