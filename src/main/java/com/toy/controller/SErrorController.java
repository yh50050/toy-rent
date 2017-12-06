package com.toy.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	public String handleError() {
		return "404";
	}

	@Override
	public String getErrorPath() {
		// TODO 自动生成的方法存根
		return ERROR_PATH;
	}

}
