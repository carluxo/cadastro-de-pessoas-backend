package br.com.carloswayand.pessoas.resources.autenticacao;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.codec.binary.Base64;

public class BasicAuthenticationService implements AuthenticationService {
	private static final String KEY = "sdhsjkhdfjshdjfhsjdhfkj544dfsdfs54d6f5s";
	protected static Map<String, String> users = new ConcurrentHashMap<>();
	protected static Set<String> authenticatedies = new ConcurrentSkipListSet<>();

	static {
		users.put("admin", "admin");
		users.put("user_test", "123123");		
	}

	@Override
	public String authenticate(String credential) {
		try {
			var payload = credential.split(":");

			if (users.containsKey(payload[0]) && users.get(payload[0]).equals(payload[1])) {
				var token = credential + ":" + KEY;
				String encoded = Base64.encodeBase64String(token.getBytes());
				
				authenticatedies.add(encoded);
				return encoded;
			}

			throw new AuthenticationException("Credenciais inválidas");

		} catch (Exception e) {
			throw new AuthenticationException("Credenciais inválidas");
		}
	}

	@Override
	public void verify(String token) {
		try {
			String[] basic = token.split(" ");
			
			if (!"Basic".equals(basic[0]) || !authenticatedies.contains(basic[1])) {
				throw new AuthenticationException("Token inválido");
			}
		} catch(Exception e) {
			throw new AuthenticationException("Token inválido");
		}
		
	}

}
