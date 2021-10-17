package br.com.carloswayand.pessoas.domain;

import javax.validation.constraints.NotBlank;

public class Autenticacao {
	@NotBlank(message="Usuário deve ser informado")
	protected String usuario;
	@NotBlank(message="Senha deve ser informada")
	protected String senha;
	
	protected Autenticacao() {
		
	}
	
	public Autenticacao(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return this.usuario + ":" + this.senha;
	}
}
