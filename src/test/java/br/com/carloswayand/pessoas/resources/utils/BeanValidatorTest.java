package br.com.carloswayand.pessoas.resources.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;

import br.com.carloswayand.pessoas.domain.Pessoa;
import br.com.carloswayand.pessoas.resources.utils.BeanValidator;

class BeanValidatorTest {

	@Test
	void validate() {
		Pessoa pessoa = new Pessoa("", null, "");		
		assertThrows(ConstraintViolationException.class, () ->  BeanValidator.validate(pessoa));
	}
}
