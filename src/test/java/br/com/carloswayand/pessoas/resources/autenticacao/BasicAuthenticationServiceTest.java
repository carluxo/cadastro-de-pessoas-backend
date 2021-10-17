package br.com.carloswayand.pessoas.resources.autenticacao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BasicAuthenticationServiceTest {

	@Test
	void deveAutenticarUsuario() {
		var service = new BasicAuthenticationService();
		String token = service.authenticate("admin:admin");
		
		assertDoesNotThrow(() -> service.verify("Basic " + token));
	}
	
	@Test
	void naoDeveAutenticarUsuario() {
		var service = new BasicAuthenticationService();
		assertThrows(AuthenticationException.class,() -> service.authenticate("admin:123"));
	}
	
	@Test
	void naoDeveVerificarTokenForjado() {
		var service = new BasicAuthenticationService();
		assertThrows(AuthenticationException.class,() -> service.verify("Basic aslkdjkasjdklajskdjakjsdklja"));
	}
	
	@Test
	void naoDeveVerificarTokenVazio() {
		var service = new BasicAuthenticationService();
		assertThrows(AuthenticationException.class,() -> service.verify(""));
	}
	
	@Test
	void deveDesautenticarUsuario() {
		var service = new BasicAuthenticationService();
		String token = service.authenticate("admin:admin");
		
		assertDoesNotThrow(() -> service.verify("Basic " + token));
		assertDoesNotThrow(() -> service.unauthenticate(token));
		assertDoesNotThrow(() -> service.unauthenticate("Basic " + token));
	}

}
