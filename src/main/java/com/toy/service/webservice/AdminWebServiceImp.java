package com.toy.service.webservice;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.toy.model.Admin;
import com.toy.service.AdminService;
import com.toy.utils.Message;

@WebService
public class AdminWebServiceImp implements AdminWebService {

	@Autowired
	AdminService adminService;

	@Override
	public String getAdmin(String uname, String passwd, int re) {
		// TODO 自动生成的方法存根
		System.out.println("调用AdminWebService。。。");
		Message<?> msg = adminService.login(uname, passwd);
		Admin admin = (Admin) msg.getData();
		if (null != admin)
			System.out.println(admin.getAdminNickname() + "--->");
		return msg.getMessage();
	}

}
