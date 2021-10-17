package br.com.carloswayand.pessoas.resources.autenticacao;

public class AuthenticationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 90095918766583955L;

	public AuthenticationException(String message) {
		super(message);
	}
}
