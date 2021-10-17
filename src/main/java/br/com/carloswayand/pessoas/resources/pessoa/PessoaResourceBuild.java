package br.com.carloswayand.pessoas.resources.pessoa;

import br.com.carloswayand.pessoas.core.data.Repository;
import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.filters.BasicAuthenticationFilter;
import br.com.carloswayand.pessoas.resources.autenticacao.AuthenticationService;
import br.com.carloswayand.pessoas.resources.autenticacao.BasicAuthenticationService;
import br.com.carloswayand.pessoas.resources.core.ResourceBuild;
import spark.Service;

public class PessoaResourceBuild extends ResourceBuild {

	public PessoaResourceBuild(Service service) {
		super(service, "/api/v1/pessoas");
	}

	@Override
	protected void initializeEndpoints() {
		Repository<Pessoa> repository = new PessoaRepository();

		service.post(path, new PessoaPostResource(repository), ResourceBuild.transform);
		service.get(path, APPLICATION_JSON, new PessoaGetResource(repository), ResourceBuild.transform);
		service.get(path + "/:id", APPLICATION_JSON, new PessoaGetByIdResource(repository), ResourceBuild.transform);
		service.patch(path + "/:id", APPLICATION_JSON, new PessoaPatchResource(repository), ResourceBuild.transform);
		service.delete(path + "/:id", new PessoaDeleteResource(repository));

		AuthenticationService authenticationService = new BasicAuthenticationService();
		BasicAuthenticationFilter filter = new BasicAuthenticationFilter(authenticationService);
		service.before(path, filter);
		service.before(path + "/:id", filter);
	}

}
