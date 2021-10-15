package br.com.carloswayand.pessoas.resources.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidCpfImp implements ConstraintValidator<ValidCpf, String> {
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	private int calcularDigito(String str, int[] peso) {
		int soma = 0;
		
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	private boolean isValid(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;
		
		for (int i = 0; i < 10; i++) {
			if (cpf.matches(i +"{11}")) {
				return false;
			}
		} 	

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}
	

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		
		return isValid(value.replaceAll("\\D", ""));
	}

}
