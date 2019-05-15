package com.spring.start.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.start.util.Retrieve;

@Service
public class KbmtService {

	@Autowired
	private TestMapper mapper;
	
	@Autowired
	private Retrieve s;
	
	public String selectNow() {
		return mapper.selectNow();
	}
	
	public List<Map<String, String>> search() {
		return s.srch("query", "sfield");
	}
}
