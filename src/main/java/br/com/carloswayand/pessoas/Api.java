package br.com.carloswayand.pessoas;

import br.com.carloswayand.pessoas.resources.pessoa.PessoaResource;
import spark.Spark;

public class Api {
	public static void main(String[] args) {
		Spark.port(8080);
		
		PessoaResource.init("/api/v1/pessoas");
		
		Spark.init();
	}
}
