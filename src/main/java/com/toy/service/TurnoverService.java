package com.toy.service;

import com.toy.utils.Message;

public interface TurnoverService {
	
	public Message<?> getListByDate();
	
	public Message<?> getAllList(int page, int size);
	
	public int getCount();
}
