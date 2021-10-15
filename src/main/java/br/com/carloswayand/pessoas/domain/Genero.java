package br.com.carloswayand.pessoas.domain;

public enum Genero {
	
	NAO_INFORMADO("Não informado"),
	MASCULINO("Masculino"),
	FEMININO("Feminino");
	
	String descricao;

	private Genero(String descricao) {
		this.descricao = descricao;
	}
}
