package com.spring.start.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.start.service.KbmtService;

@Controller
public class KbmtController {

	@Autowired
	private KbmtService service;
	
	@RequestMapping("/home.do")
	public String home(Locale locale, Model model) {
		System.out.println("home");
		return "home";
	}
	
	@RequestMapping("/main.do")
	public String main() {
		return "kbmt/main";
	}
	
	@RequestMapping(value="/search.do", produces="application/text; charset=utf8")
	@ResponseBody
	public String search(HttpServletRequest req) throws Exception {
		
		//챗봇 질의문 Simul
		String ichatIp = "http://211.39.140.169";
		String ichatPort = "17300";
		String projectId = "ba2a28894c27";
		String query = req.getParameter("query");
		String apiURI = ichatIp+":"+ichatPort+"/api/v1/demo/simulation";
		
		StringBuilder sb = new StringBuilder();
		String  jsonRequestBuf  =  "{\r\n" + 
				"\"projectId\":\""+projectId+"\",\r\n" + 
				"\"query\":\""+query+"\"\r\n" + 
				"}"; 
		System.out.println("[질문] " + jsonRequestBuf);
		String result = service.sendPost(sb.toString(),apiURI, jsonRequestBuf); 
		System.out.println("[챗봇결과] " + result);
		
		//SF-1 검색
		Map<String, Object> mSearch = service.search(result);
		String category = (String) mSearch.get("category");
		List<String> lSearch = (List<String>) mSearch.get("result");
		
		//질문 유형 별 답변 생성
		switch (category) {
			case "지역 관련 인물":
				if(lSearch.size() >0) {
					sb.append("지역 관련된 목록입니다.<br>");
					for (int i = 0; i < lSearch.size(); i++) {
						sb.append(i + 1).append(". ").append(lSearch.get(i)).append("<br>");
					}		
				}
				break;
			case "특정인물 내용":
				if(lSearch.size() >0) {
					sb.append("인물 관련된 목록입니다.<br>");
					for (int i = 0; i < lSearch.size(); i++) {
						sb.append(i + 1).append(". ").append(lSearch.get(i)).append("<br>");
					}
				}
				
				break;
			case "특정인물 발언 내용":
				if(lSearch.size() >0) {
					sb.append("인물에 발언한 목록입니다.<br>");
					for (int i = 0; i < lSearch.size(); i++) {
						sb.append(i + 1).append(". ").append(lSearch.get(i)).append("<br>");
					}
				}
				break;
			case "특정인물 시간대 활동":
				if(lSearch.size() >0) {
					for (int i = 0; i < 1; i++) {
						sb.append(lSearch.get(i)).append(" 입니다.");
					}
				}
				break;
			case "함께 언급된 인물":
				if(lSearch.size() >0) {
					for (int i = 0; i < lSearch.size(); i++) {
						for(String usr : lSearch.get(i).split(",")) {
							if(!sb.toString().contains(usr.trim())) {
								if(sb.length()>0) {
									sb.append(", ").append(usr.trim());			
								}else {
									
									sb.append(usr.trim());			
								}
							}
						}
					}
					sb.append(" 입니다.");
				}
				
				break;
		}

		
		if(sb.length()==0) {
			if(query.equals("")) {
				sb.append("질문 내용이 없습니다." );		
			}else {
				sb.append("질문에 부합되는 답변이 없습니다.<br> 정확히 다시 문의 부탁드립니다." );		
			}
		}
		
		return sb.toString();
	}
}
