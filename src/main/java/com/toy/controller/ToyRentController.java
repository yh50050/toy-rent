package com.toy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.toy.model.Admin;
import com.toy.model.Employee;
import com.toy.model.Member;
import com.toy.model.ToyRent;
import com.toy.service.MemberService;
import com.toy.service.ToyRentService;
import com.toy.service.ToyService;
import com.toy.utils.Message;

@Controller
@RequestMapping("/toy-rent")
public class ToyRentController {

	@Autowired
	ToyService toyService;

	@Autowired
	MemberService memberService;

	@Autowired
	ToyRentService toyRentService;

	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public String getList(Model model, String name, String type, Integer page, Integer size, String info) {
		if (null == name)
			name = "";
		if (null == type)
			type = "";
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		Message<?> msg = null;
		msg = toyService.getListByNameAndType(name, type, "1", page, size);
		model.addAttribute("message", msg);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		model.addAttribute("info", info);
		return "toy-rent";
	}

	@RequestMapping(value = "/rent", method = RequestMethod.GET)
	public String getList(Model model, String name, String type, Integer page, long toyId) {
		if (null == name)
			name = "";
		if (null == type)
			type = "";
		if (null == page)
			page = 0;
		Message<?> msg = null;
		msg = toyService.getById(toyId);
		model.addAttribute("message", msg);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		model.addAttribute("page", page);
		return "rent";
	}

	@RequestMapping(value = "/toyRent", method = RequestMethod.GET)
	public ModelAndView toyRent(ModelAndView mv, HttpSession session, String name, String type, Integer page,
			ToyRent toyRent) throws Exception {
		if (null == name)
			name = "";
		if (null == type)
			type = "";
		if (null == page)
			page = 0;
		Message<?> msg = null;
		Long empId = null;
		String admin = (String) session.getAttribute("admin");
		if (!"1".equals(admin)) {
			empId = ((Employee) session.getAttribute("user")).getEmployeeId();
			toyRent.setTrEmployeeType("0");
		} else {
			empId = ((Admin) session.getAttribute("user")).getAdminId();
			toyRent.setTrEmployeeType("1");
		}
		toyRent.setTrEmpolyeeId(empId);
		msg = toyRentService.save(toyRent);
		mv.addObject("info", msg.getMessage());
		mv.addObject("name", name);
		mv.addObject("type", type);
		mv.addObject("page", page);
		mv.setViewName("redirect:/toy-rent/getList");
		return mv;
	}

	@RequestMapping(value = "/rentList", method = RequestMethod.GET)
	public String rentList(Model model, String key, String isReturn, Integer page, Integer size) throws Exception {
		Message<?> msg = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		if (null == key || "".equals(key)) {
			msg = toyRentService.getIsReturnList(isReturn, page, size);
		} else {
			Member member = (Member) memberService.getByCodeOrPhone(key).getData();
			if (null != member) {
				msg = toyRentService.getMemberIsReturnList(isReturn, member.getMemberId(), page, size);
			} else
				msg = toyRentService.getMemberIsReturnList(isReturn, -1, page, size);
			model.addAttribute("key", key);
		}
		model.addAttribute("message", msg);
		model.addAttribute("page", page);
		return "rent-list";
	}

	@RequestMapping(value = "/allRentList", method = RequestMethod.GET)
	public String allRentList(Model model, String key, Integer page, Integer size) throws Exception {
		Message<?> msg = null;
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;

		if (null == key || "".equals(key)) {
			msg = toyRentService.getAllList(page, size);
		} else {
			Member member = (Member) memberService.getByCodeOrPhone(key).getData();
			if (null != member) {
				msg = toyRentService.getMemberAllList(member.getMemberId(), page, size);
			} else
				msg = toyRentService.getMemberAllList(-1, page, size);
			model.addAttribute("key", key);
		}
		model.addAttribute("message", msg);
		return "rent-list2";
	}

	@RequestMapping(value = "/returnToy", method = RequestMethod.GET)
	public ModelAndView returnToy(ModelAndView mv, String isReturn, Integer page, String key, long trId)
			throws Exception {
		Message<?> msg = null;
		if (null == page)
			page = 0;
		msg = toyRentService.returnToy(trId);
		mv.addObject("info", msg.getMessage());
		mv.addObject("isReturn", isReturn);
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.setViewName("redirect:/toy-rent/rentList");
		return mv;
	}

}
