package com.toy.service;

import com.toy.model.Member;
import com.toy.utils.Message;

public interface MemberService {
	
	public Message<?> save(Member member) throws Exception;
	
	public Message<?> findByNameOrPhoneOrCode(String key, int page, int size);
	
	public Message<?> delete(long memberId) throws Exception;
	
	public int getCount(String key);
	
	public Message<?> getById(long memberId) throws Exception;
	
	public Message<?> getByPhone(String phone) throws Exception;
	
	public Message<?> getByCode(String code) throws Exception;
	
	public Message<?> getByCodeOrPhone(String key) throws Exception;
	
	public Message<?> recharge(String memberCode, Integer money) throws Exception;
	
	public Message<?> getRecord(long memberId) throws Exception;
}
