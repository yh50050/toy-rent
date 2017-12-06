package com.toy.service;

import com.toy.model.Employee;
import com.toy.utils.Message;

public interface EmployeeService {

	public Message<?> login(String employeeCode, String empoyeePassword);

	public Message<?> save(Employee employee) throws Exception;

	public Message<?> update(Employee employee) throws Exception;

	public Message<?> delete(long employeeId, String code) throws Exception;

	public Message<?> getListByNameOrCode(String key, int page, int size);

	public Message<?> getById(long employeeId);

	public int getCount(String key);
	
	public Message<?> checkEmployeeCode(String code);
}
