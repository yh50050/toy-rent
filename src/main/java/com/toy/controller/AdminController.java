package com.toy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toy.model.Admin;
import com.toy.service.AdminService;
import com.toy.utils.JsonUtils;
import com.toy.utils.Message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "AdminController", tags = "AdminController")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	/**
	 * 管理员登陆
	 * 
	 * @param adminUserName
	 * @param adminPassword
	 * @param session
	 * @return
	 */
	@ApiOperation(value = "管理员登陆", notes = "通过用户名和密码查找用户，成功后将用户对象放入session，并跳转到主页")
	@ApiImplicitParams({ @ApiImplicitParam(name = "uname", value = "管理员登陆用户名", required = true, dataType = "String"),
			@ApiImplicitParam(name = "passwd", value = "管理员登陆密码", required = true, dataType = "String") })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(String uname, String passwd, Model model, HttpSession session) {
		Message<?> message = null;
		if (null != uname && null != passwd) {
			message = adminService.login(uname, passwd);
			if (Message.MSG_SUCCESS.equals(message.getMessage())) {
				session.setAttribute("user", message.getData());
				session.setAttribute("admin", "1");
				message.setData(null);
			}
		} else {
			message = new Message<Admin>(HttpStatus.OK, Message.MSG_ERROR, null);
		}
		return JsonUtils.objectToJson(message);
	}

	/**
	 * 修改管理员信息
	 * 
	 * @param admin
	 * @return
	 */
	@ApiOperation(value = "管理员修改密码", notes = "管理员修改密码，成功后返回该管理员信息")
	@RequestMapping(value = "/mdifyPassword", method = RequestMethod.POST)
	@ResponseBody
	public String mdifyPassword(@ApiParam(name = "admin", value = "管理员对象", required = true) Admin admin) {
		// Message<?> message = null;
		// if(null!=admin)
		// {
		// message = adminService.update(admin);
		// return null;
		// }
		return null;
	}

	/**
	 * 获取管理员信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAdminInfo/{adminId}", method = RequestMethod.GET)
	@ResponseBody
	public String getAdminInfo(HttpSession session, String adminId) {
		Message<?> message = null;
		if (null != adminId)
			message = adminService.getById(Long.parseLong(adminId));
		else {
			Admin admin = (Admin) session.getAttribute("admin");
			admin.setAdminPassword("");
			new Message<Admin>(HttpStatus.OK, Message.MSG_SUCCESS, admin);
		}
		return JsonUtils.objectToJson(message);
	}

	/**
	 * 管理员退出
	 * 
	 * @param session
	 * @return
	 */
	@ApiOperation(value = "管理员登出", notes = "销毁该用户的session，并跳转到登陆页面")
	@ApiImplicitParam(name = "session", value = "HttpSession对象", required = false, dataType = "javax.servlet.http.HttpSession")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
