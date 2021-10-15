package br.com.carloswayand.pessoas.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PessoaTest {

	@Test
	void setAndGetId() {
		Pessoa pessoa = new Pessoa("SET ID", LocalDate.now(), "");
		pessoa.setId("1");
		
		assertEquals("1", pessoa.getId());
	}
	
	@Test
	void dontSetId() {
		Pessoa pessoa = new Pessoa("SET ID", LocalDate.now(), "");
		pessoa.setId("");
		
		assertNull(pessoa.getId());
		
		pessoa.setId(null);
		assertNull(pessoa.getId());
		
		assertThrows(NumberFormatException.class, () -> pessoa.setId("A"));
		
		pessoa.setId("1");
		assertNotNull(pessoa.getId());
		assertEquals("1", pessoa.getId());
		
		pessoa.setId("2");
		assertEquals("1", pessoa.getId());
		
	}
	
	@Test
	void setCreatedAt() {
		Pessoa pessoa = new Pessoa("SET ID", LocalDate.now(), "");
		pessoa.update();
		assertNotNull(pessoa.getCreatedAt());
	}
	
	@Test
	void setUpdatedAt() {
		Pessoa pessoa = new Pessoa("SET ID", LocalDate.now(), "");
		pessoa.update();
		assertNotNull(pessoa.getCreatedAt());
		
		pessoa.update();
		assertNotNull(pessoa.getUpdatedAt());
		
	}

}
