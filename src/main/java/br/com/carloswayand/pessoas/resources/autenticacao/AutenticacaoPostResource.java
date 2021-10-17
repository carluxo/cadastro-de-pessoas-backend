package br.com.carloswayand.pessoas.resources.autenticacao;

import br.com.carloswayand.pessoas.domain.Autenticacao;
import br.com.carloswayand.pessoas.resources.utils.BeanValidator;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import spark.Request;
import spark.Response;
import spark.Route;

public class AutenticacaoPostResource implements Route   {
	protected AuthenticationService service; 
	
	public AutenticacaoPostResource(AuthenticationService service) {
		this.service = service;
	}
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		Autenticacao autenticacao = JsonUtils.fromJsonToObject(request.body(), Autenticacao.class);
		BeanValidator.validate(autenticacao);
		this.service.authenticate(autenticacao.toString());
		return "";
	}

}
