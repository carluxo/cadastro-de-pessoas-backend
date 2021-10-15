package br.com.carloswayand.pessoas.resources.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BeanValidator {
	private static ValidatorFactory factory;
	private static Validator validator;
	
	static {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	private BeanValidator() {
		// TODO Auto-generated constructor stub
	}
	
	public static void validate(Object obj) {
		Set<ConstraintViolation<Object>> validate = validator.validate(obj);
		
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
}
