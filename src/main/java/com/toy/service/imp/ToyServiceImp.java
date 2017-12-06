package com.toy.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.toy.model.EmployeeAccount;
import com.toy.model.Toy;
import com.toy.repository.ToyRepository;
import com.toy.service.ToyService;
import com.toy.utils.Message;
import com.toy.utils.PagingUtils;

@Service
public class ToyServiceImp implements ToyService {

	@Autowired
	ToyRepository toyRepository;

	/**
	 * 新建或修改一条玩具信息
	 */
	@Override
	public Message<?> save(Toy toy) {
		Message<?> msg = null;
		try {
			toyRepository.save(toy);
			msg = new Message<Toy>(HttpStatus.OK, Message.MSG_SUCCESS, toy);
		} catch (Exception e) {
			msg = new Message<Toy>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return msg;
	}

	/*
	 * 根据玩具名称或类型模糊匹配已上架玩具 （非 Javadoc）
	 * 
	 * @see com.toy.service.ToyService#getListByNameOrType(java.lang.String, int,
	 * int)
	 */
	@Override
	public Message<?> getListByNameOrType(String key, String state, int page, int size) {
		Message<?> msg = null;
		List<Toy> toys = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		toys = toyRepository.findByToyNameOrToyType("%" + key + "%", state, page1, size);
		int count = getCount(key, state);
		PagingUtils<?> paging = new PagingUtils<Toy>(page, size, count, toys);
		msg = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return msg;
	}

	/**
	 * 得到条件匹配的条数
	 */
	@Override
	public int getCount(String key, String state) {
		int result = 0;
		result = toyRepository.getCount("%" + key + "%", "%" + state + "%");
		return result;
	}

	/**
	 * 通过id删除玩具
	 */
	@Override
	public Message<?> deleteById(long toyId) {
		Message<?> msg = null;
		try {
			toyRepository.delete(toyId);
			msg = new Message<Toy>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} catch (Exception e) {
			msg = new Message<Toy>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return msg;
	}

	/**
	 * 根据id得到玩具
	 */
	@Override
	public Message<?> getById(long toyId) {
		Message<?> msg = null;
		try {
			Toy toy = toyRepository.findOne(toyId);
			msg = new Message<Toy>(HttpStatus.OK, Message.MSG_SUCCESS, toy);
		} catch (Exception e) {
			msg = new Message<Toy>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return msg;
	}

	/**
	 * 上、下架玩具
	 */
	@Override
	public Message<?> modifyState(long toyId) {
		Message<?> msg = null;
		Toy toy = null;
		try {
			toy = toyRepository.findOne(toyId);
			if (null != toy) {
				toy.setToyIsRent("1".equals(toy.getToyIsRent()) ? "0" : "1");
			}
			toyRepository.save(toy);
			msg = new Message<String>(HttpStatus.OK, Message.MSG_SUCCESS, toy.getToyIsRent());
		} catch (Exception e) {
			msg = new Message<EmployeeAccount>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return msg;
	}

	/**
	 * 通过类型和名称匹配
	 */
	@Override
	public Message<?> getListByNameAndType(String name, String type, String state, int page, int size) {
		Message<?> msg = null;
		List<Toy> toys = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		toys = toyRepository.findByToyNameAndToyType("%" + name + "%", "%" + type + "%", state, page1, size);
		int count = getCount2(name, type);
		PagingUtils<?> paging = new PagingUtils<Toy>(page, size, count, toys);
		msg = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return msg;
	}

	@Override
	public int getCount2(String name, String type) {
		int result = 0;
		result = toyRepository.getCount2("%" + name + "%", "%" + type + "%");
		return result;
	}
}
