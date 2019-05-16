package com.spring.start.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/search.do")
	@ResponseBody
	public String search(HttpServletRequest req) throws Exception {
		
		//챗봇 질의문 Simul
		String ichatIp = "http://211.39.140.169";
		String ichatPort = "17300";
		String projectId = "ba2a28894c27";
//		String query = req.getParameter("query");
		String query = "12월 김정은은 어디에 있었어?";
		String apiURI = ichatIp+":"+ichatPort+"/api/v1/demo/simulation";
		
		StringBuilder sb = new StringBuilder();
		String  jsonRequestBuf  =  "{\r\n" + 
				"\"projectId\":\""+projectId+"\",\r\n" + 
				"\"query\":\""+query+"\"\r\n" + 
				"}"; 
		System.out.println("[질문] " + jsonRequestBuf);
		String result = service.sendPost(sb.toString(),apiURI, jsonRequestBuf); 
		System.out.println("[챗봇결과] " + result);
		
		List<String> lSearch = service.search(result);
		
		return lSearch.toString();
	}
}
