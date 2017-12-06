package com.toy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.toy.model.Employee;
import com.toy.utils.JsonUtils;
import com.toy.utils.Message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 员工操作接口
 * 
 * @author 枫茗丿love
 *
 */
@Api(value = "EmployeeController", tags = "EmployeeController")
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

	/**
	 * 员工登录
	 * 
	 * @param uname
	 * @param passwd
	 * @param session
	 * @return
	 */
	@ApiOperation(value = "员工登录", notes = "")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(String uname, String passwd, HttpSession session) {
		Message<?> message = null;
		if (null != uname && null != passwd) {
			message = employeeService.login(uname, passwd);
			if (Message.MSG_SUCCESS.equals(message.getMessage())) {
				session.setAttribute("user", message.getData());
				message.setData(null);
			}
		} else {
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_ERROR, null);
		}
		return JsonUtils.objectToJson(message);
	}

	/**
	 * 根据员工部门和姓名模糊匹配
	 * 
	 * @param branch
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/query-list", method = RequestMethod.GET)
	public String queryList(Model model, String key, Integer page, Integer size) {
		if (null == key)
			key = "";
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		Message<?> message = null;
		message = employeeService.getListByNameOrCode(key, page, size);
		model.addAttribute("message", message);
		model.addAttribute("key", key);
		return "employee";
	}

	/**
	 * 添加一名员工
	 * 
	 * @param mv
	 * @param employee
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(Employee employee, ModelAndView mv, String key, Integer page, Integer size)
			throws Exception {
		Message<?> message = null;
		message = employeeService.save(employee);
		mv.addObject("add", message.getMessage());
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.setViewName("redirect:/employee/query-list");
		return mv;
	}

	/**
	 * 根据员工id得到员工信息
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> get(ModelAndView mv, long empId) {
		Message<?> message = null;
		message = employeeService.getById(empId);
		return message;
	}

	/**
	 * 修改员工信息
	 * 
	 * @param employee
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Employee employee, ModelAndView mv, String key, Integer page, Integer size)
			throws Exception {
		Message<?> message = null;
		message = employeeService.update(employee);
		mv.addObject("update", message.getMessage());
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.setViewName("redirect:/employee/query-list");
		return mv;
	}

	/**
	 * 删除员工
	 * 
	 * @param empId
	 * @param mv
	 * @return
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView del(String empId, ModelAndView mv, String key, Integer page, Integer size, String code)
			throws Exception {
		Message<?> message = null;
		message = employeeService.delete(Long.parseLong(empId), code);
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.addObject("del", message.getMessage());
		mv.setViewName("redirect:/employee/query-list");
		return mv;
	}

	/**
	 * 根据编号模糊匹配员工账号
	 * 
	 * @param model
	 * @param key
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String accounts(Model model, String key, Integer page, Integer size) {
		if (null == key)
			key = "";
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		Message<?> message = null;
		message = employeeAccountService.getAccountListByCode(key, page, size);
		model.addAttribute("message", message);
		model.addAttribute("key", key);
		return "employee-account";
	}

	/**
	 * 验证员工编号是否被使用
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> checkCode(String code) {
		Message<?> message = null;
		message = employeeService.checkEmployeeCode(code);
		return message;
	}

	/**
	 * 停封或解封账号
	 * 
	 * @param empAccount
	 * @return
	 */
	@RequestMapping(value = "/on-off", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> on_Off(String accountId) {
		Message<?> message = null;
		message = employeeAccountService.modifyState(Long.parseLong(accountId));
		return message;
	}
}
