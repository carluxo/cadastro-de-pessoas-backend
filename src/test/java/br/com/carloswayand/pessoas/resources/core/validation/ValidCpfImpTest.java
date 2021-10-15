package br.com.carloswayand.pessoas.resources.core.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidCpfImpTest {
	
	protected ValidCpfImp validador = new ValidCpfImp();

	@Test
	void deveValidar() {
		assertTrue(validador.isValid("056.313.717-74", null));
	}
	
	@Test
	void naoDeveValidarVazio() {
		assertFalse(validador.isValid("", null));
	}
	
	@Test
	void naoDeveValidarComCaracteresInvalidos() {
		assertFalse(validador.isValid("ASVC---lkjlklds123", null));
	}

	@Test
	void naoDeveValidarComTamanhoMaiorQue11() {
		assertFalse(validador.isValid("123456852580000", null));
	}
	
	@Test
	void naoDeveValidarComTamanhoMenorQue11() {
		assertFalse(validador.isValid("067174", null));
	}
	
	@Test
	void naoDeveValidarNulo() {
		assertFalse(validador.isValid(null, null));
	}
	
	@Test
	void naoDeveValidarComSequencia() {
		assertFalse(validador.isValid("000000000000", null));
		assertFalse(validador.isValid("88888888888", null));
		assertFalse(validador.isValid("11111111111", null));
	}
	
	
}
