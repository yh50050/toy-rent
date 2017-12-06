package com.toy.service;

import com.toy.model.ToyRent;
import com.toy.utils.Message;

public interface ToyRentService {

	public Message<?> save(ToyRent toyRent) throws Exception;

	public Message<?> getIsReturnList(String isReturn, int page, int size);

	public Message<?> getMemberIsReturnList(String isReturn, long memberId, int page, int size);
	
	public Message<?> getMemberAllList(long memberId, int page, int size);
	
	public Message<?> getMemberRentList(long memberId, int page, int size);

	public Message<?> getAllList(int page, int size);
	
	public int getMemberIsReturnCount(String isReturn, long memberId);
	
	public int getMemberListCount(long memberId);
	
	public int getIsReturnCount(String isReturn);
	
	public int getMemberRentCount(long memberId);
	
	public int getCount();
	
	public Message<?> getById(long toyRentId);
	
	public Message<?> update(ToyRent toRent);
	
	public Message<?> returnToy(long trId) throws Exception;
}
