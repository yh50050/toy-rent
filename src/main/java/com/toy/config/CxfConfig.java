package com.toy.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.toy.service.webservice.AdminWebService;
import com.toy.service.webservice.AdminWebServiceImp;

@Configuration
public class CxfConfig {

	@Autowired
	private Bus bus;

	@Bean
    public AdminWebService adminService() {
        return new AdminWebServiceImp();
    }

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, adminService());
		endpoint.publish("/admin");
		return endpoint;
	}

}
