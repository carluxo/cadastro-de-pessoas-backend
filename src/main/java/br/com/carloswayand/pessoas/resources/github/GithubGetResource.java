package br.com.carloswayand.pessoas.resources.github;

import java.util.HashMap;
import java.util.Map;

import br.com.carloswayand.pessoas.resources.core.GetResource;
import br.com.carloswayand.pessoas.resources.utils.JsonUtils;
import spark.Request;

public class GithubGetResource implements GetResource<String> {

	@Override
	public String handleGet(Request request) {
		Map<String, String> map = new HashMap<>();
		map.put("backend", "https://github.com/carluxo/cadastro-de-pessoas-backend");
		map.put("frontend", "https://github.com/carluxo/cadastro-de-pessoas-frontend");
		return JsonUtils.fromObjectToJson(map);
	}

}
