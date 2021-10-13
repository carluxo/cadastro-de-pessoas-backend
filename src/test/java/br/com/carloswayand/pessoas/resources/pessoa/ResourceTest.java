package br.com.carloswayand.pessoas.resources.pessoa;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ResourceTest implements BeforeAllCallback {	
	static String host;

	public static String getPath(String endpoint) {
		return host + endpoint;
	}

	@Override
	public void beforeAll(ExtensionContext arg) throws Exception {
		host = "http://localhost:8080";	
	}
	
}
