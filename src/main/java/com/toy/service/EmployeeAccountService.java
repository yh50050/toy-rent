package com.toy.service;

import com.toy.model.EmployeeAccount;
import com.toy.utils.Message;

public interface EmployeeAccountService {

	public Message<?> update(EmployeeAccount empAccount);

	public Message<?> del(long accountId);

	public Message<?> getAccountListByCode(String key, int page, int size);

	public int getCount(String key);
	
	public Message<?> modifyState(long accountId);
}
