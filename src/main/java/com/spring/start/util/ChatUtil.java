package com.spring.start.util;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

@Repository
public class ChatUtil {
	
	/*
	 * 날짜형식 변경
	 */
	public String convertDate(String ori) {
		return "";
	}
	
	/*
	 * 챗봇 리턴json 매핑
	 */
	public Map<String, String> parsingJson(String json) throws Exception {
		Map<String, String> result = new HashMap<>();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
		JSONObject response = (JSONObject) jsonObject.get("response");
		JSONArray parameter = (JSONArray) response.get("parameterValueList");
		
		String category = response.get("topIntentName").toString();
		result.put("category", category);
		
		for (int i = 0; i < parameter.size(); i++) {
			JSONObject params = (JSONObject) parameter.get(i);
			String name = params.get("paramName").toString();
			String value = params.get("value").toString();
			
			if ("".equals(value)) {
				continue;
			}

			result.put(name, value);
		}
		
		return result;
	}
}
