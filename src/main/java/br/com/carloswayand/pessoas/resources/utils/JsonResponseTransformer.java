package br.com.carloswayand.pessoas.resources.utils;

import spark.ResponseTransformer;

public class JsonResponseTransformer implements ResponseTransformer {
	public String render(Object model) throws Exception {
		return JsonUtils.fromObjectToJson(model);
	}
}
