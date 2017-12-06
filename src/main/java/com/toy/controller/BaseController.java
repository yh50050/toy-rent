package com.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.toy.service.AdminService;
import com.toy.service.EmployeeAccountService;
import com.toy.service.EmployeeService;
import com.toy.service.MemberService;
import com.toy.service.ToyRentService;
import com.toy.service.ToyService;
import com.toy.service.TurnoverService;

@Controller
public class BaseController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeAccountService employeeAccountService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ToyService toyService;
	
	@Autowired
	ToyRentService toyRentService;
	
	@Autowired
	TurnoverService turnoverService;
}
