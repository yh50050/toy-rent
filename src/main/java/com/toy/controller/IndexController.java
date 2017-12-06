package com.toy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toy.utils.Message;

import io.swagger.annotations.ApiOperation;

@Controller
public class IndexController extends BaseController {

	@ApiOperation(value = "跳转会员页面", notes = "")
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public String member(Model model, Integer page, Integer size) {
		Message<?> message = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		message = memberService.findByNameOrPhoneOrCode("", page, size);
		model.addAttribute("message", message);
		return "member";
	}

	@ApiOperation(value = "跳转会员充值页面", notes = "")
	@RequestMapping(value = "/member-recharge", method = RequestMethod.GET)
	public String memberRecharge() {
		return "member-recharge";
	}

	@ApiOperation(value = "跳转营业员页面", notes = "")
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String employee(Model model, Integer page, Integer size) {
		Message<?> message = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		message = employeeService.getListByNameOrCode("", page, size);
		model.addAttribute("message", message);
		return "employee";
	}

	@ApiOperation(value = "跳转员工账号页面", notes = "")
	@RequestMapping(value = "/employee-account", method = RequestMethod.GET)
	public String employee_account(Model model, Integer page, Integer size) {
		Message<?> message = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		message = employeeAccountService.getAccountListByCode("", page, size);
		model.addAttribute("message", message);
		return "employee-account";
	}

	@ApiOperation(value = "跳转玩具页面", notes = "")
	@RequestMapping(value = "/toy", method = RequestMethod.GET)
	public String toy(Model model, Integer page, Integer size) {
		Message<?> msg = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		msg = toyService.getListByNameOrType("", "1", page, size);
		model.addAttribute("message", msg);
		return "toy";
	}

	@ApiOperation(value = "跳转上、下架玩具页面", notes = "")
	@RequestMapping(value = "/up-down-toy", method = RequestMethod.GET)
	public String upDownToy(Model model, Integer page, Integer size) {
		Message<?> msg = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		msg = toyService.getListByNameOrType("", "%", page, size);
		model.addAttribute("message", msg);
		return "up-down-toy";
	}

	@ApiOperation(value = "跳转玩具出租页面", notes = "")
	@RequestMapping(value = "/toy-rent", method = RequestMethod.GET)
	public String toy_rent(Model model, Integer page, Integer size) {
		Message<?> msg = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		msg = toyService.getListByNameAndType("", "", "1", page, size);
		model.addAttribute("message", msg);
		return "toy-rent";
	}

	@ApiOperation(value = "跳转玩具归还页面", notes = "")
	@RequestMapping(value = "/rent-list", method = RequestMethod.GET)
	public String rentList(Model model, Integer page, Integer size) {
		Message<?> msg = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		msg = toyRentService.getIsReturnList("0", page, size);
		model.addAttribute("message", msg);
		return "rent-list";
	}
	
	@ApiOperation(value = "跳转玩具出租记录页面", notes = "")
	@RequestMapping(value = "/rent-list2", method = RequestMethod.GET)
	public String rentList2(Model model, Integer page, Integer size) {
		Message<?> msg = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		msg = toyRentService.getAllList(page, size);
		model.addAttribute("message", msg);
		return "rent-list2";
	}

	@ApiOperation(value = "用户登出", notes = "跳转回登录页面")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String login(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
