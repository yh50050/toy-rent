package com.toy.service.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface AdminWebService {

	@WebMethod
	public String getAdmin(String uname, String passwd, int re);

}
