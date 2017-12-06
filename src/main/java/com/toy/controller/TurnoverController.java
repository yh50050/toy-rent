package com.toy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/turnover")
public class TurnoverController {

	
	public String getList(Integer page, Integer size) {
		return "";
	};
	
}
