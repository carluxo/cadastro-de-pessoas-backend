package br.com.carloswayand.pessoas;

import br.com.carloswayand.pessoas.resources.pessoa.PessoaResource;

public class Api {
	public static void main(String[] args) {
		spark.Service service = spark.Service.ignite();
		service.port(8080);
		
		PessoaResource.init(service, "/api/v1/pessoas");
		
		service.init();
	}
}
