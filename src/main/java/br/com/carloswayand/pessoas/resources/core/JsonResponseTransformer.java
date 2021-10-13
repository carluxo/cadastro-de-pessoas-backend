package br.com.carloswayand.pessoas.resources.core;

import spark.ResponseTransformer;

public class JsonResponseTransformer implements ResponseTransformer {
	public String render(Object model) throws Exception {
		return JsonUtils.fromObjectToJson(model);
	}
}
