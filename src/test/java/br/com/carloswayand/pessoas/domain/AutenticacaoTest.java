package br.com.carloswayand.pessoas.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AutenticacaoTest {

	@Test
	void deveCriarNovaAutenticacao() {
		assertDoesNotThrow(() -> new Autenticacao("admin", "admin"));
	}
	
	@Test
	void deveCriarStringCredencial() {
		 Autenticacao autenticacao = new Autenticacao("admin", "admin");
		 assertEquals("admin:admin", autenticacao.toString());
	}

}
