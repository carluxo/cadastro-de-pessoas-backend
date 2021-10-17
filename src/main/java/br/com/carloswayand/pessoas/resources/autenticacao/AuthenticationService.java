package br.com.carloswayand.pessoas.resources.autenticacao;

public interface AuthenticationService {
	String authenticate(String credential);
	void verify(String token);
	void unauthenticate(String token);
}
