package com.spring.start.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void search() {
		
		//챗봇 질의문 Simul
		String ichatIp = "http://211.39.140.169"; 
		String ichatPort = "17300"; 
		String projectId = "ba2a28894c27"; 
		String query = "어제 김정은은 어디에 있었어?";
		String apiURI = ichatIp+":"+ichatPort+"/api/v1/demo/simulation";
		
		StringBuilder sb = new StringBuilder();
		String  jsonRequestBuf  =  "{\r\n" + 
				"\"projectId\":\""+projectId+"\",\r\n" + 
				"\"query\":\""+query+"\"\r\n" + 
				"}"; 
		String result = service.sendPost(sb.toString(),apiURI, jsonRequestBuf); 
		System.out.println(jsonRequestBuf);
		
		System.out.println(result);

		
		System.out.println(service.search());
	}
}
