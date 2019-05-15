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
		System.out.println(service.search());
	}
}
