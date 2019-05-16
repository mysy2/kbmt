package com.spring.start.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

@Repository
public class ChatUtil {
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
	
	/*
	 * 검색 쿼리 전환
	 */
	public Map<String, String> convertKeyword(Map<String, String> map) {
		Map<String, String> result = new HashMap<>();
		String keyword = "";
		String dfield = "";
		String sdate = "";
		String edate = "";
		Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		
		while (iter.hasNext()) {
			String key = iter.next();
			
			if ("category".equals(key)) {
				String category = map.get(key);
				
				switch (category) {
				case "지역 관련 인물":
					dfield = "AREA";
					break;
				case "특정인물 내용":
					dfield = "TITLE";
					break;
				case "특정인물 발언 내용":
					dfield = "COMMENT";
					break;
				case "특정인물 시간대 활동":
					dfield = "DATE";
					break;
				case "함께 언급된 인물":
					dfield = "USER";
					break;
				}
			} else if (key.contains("사람")) {
				keyword += map.get(key) + ":PS ";
			} else if (key.contains("장소")) {
				keyword += map.get(key) + ":LS ";
			} else if (key.contains("date")) {
				String date = map.get(key);
				Matcher m = p.matcher(date);
				
				if (m.find()) {
					sdate = date;
					edate = date;
				}
			}
		}
		
		result.put("query", keyword);
		result.put("sdate", sdate);
		result.put("edate", edate);
		result.put("dfield", dfield);
		
		return result;
	}
}
