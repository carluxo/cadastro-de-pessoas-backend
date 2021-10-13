package br.com.carloswayand.pessoas.core.data;

public class IdentifiableNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4989852571283907627L;

	public IdentifiableNotFoundException() {
		super();
	}

	IdentifiableNotFoundException(String message) {
		super(message);
	}
}
