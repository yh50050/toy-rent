package com.annet.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.toy.ToyApplication;
import com.toy.model.Admin;
import com.toy.model.Employee;
import com.toy.repository.AdminRepository;
import com.toy.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToyApplication.class)
public class TestMain {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void test01() {
		Admin admin = null;
		admin = adminRepository.findByAdminUserNameAndAdminPassword("yan321", "yan321++");
		System.out.println(admin + "---");
	}

	@Test
	public void test02() {
		for(int i=10; i<100; i++) {
			Employee emp = new Employee();
			emp.setEmployeeName("提莫"+i);
			emp.setEmployeeAddress("陕西西安");
			emp.setEmployeeBirth("2017-11-0"+i);
			emp.setEmployeeCode("AE21ee1"+i);
			emp.setEmployeeEmail("vkmvsdksd2"+i+"@163.com");
			emp.setEmployeePhone("192637579"+i);
			emp.setEmployeeOnJop("1");
			emp.setEmployeeBranch("营业员");
			if(i%2==0)
			emp.setEmployeeSex("女");
			employeeRepository.save(emp);
		}
		
	}
}
