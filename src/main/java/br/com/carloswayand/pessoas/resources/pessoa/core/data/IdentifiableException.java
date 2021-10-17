package br.com.carloswayand.pessoas.resources.pessoa.core.data;

public class IdentifiableException extends RuntimeException {
	private static final long serialVersionUID = 1991711451201410679L;
	private final String field;
	
	public IdentifiableException(String field, String message) {
		super(message);
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
