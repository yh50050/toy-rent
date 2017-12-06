package com.toy.service;

import com.toy.model.Toy;
import com.toy.utils.Message;

public interface ToyService {

	public Message<?> save(Toy toy);

	public Message<?> getListByNameOrType(String key, String state, int page, int size);

	public Message<?> deleteById(long toyId);
	
	public Message<?> getById(long toyId);

	public int getCount(String key, String state);
	
	public Message<?> modifyState(long toyId);
	
	public Message<?> getListByNameAndType(String name, String type, String state, int page, int size);
	
	public int getCount2(String name, String type);

}
