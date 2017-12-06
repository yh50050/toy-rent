package com.toy.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.model.EmployeeAccount;
import com.toy.repository.EmployeeAccountRepository;
import com.toy.service.EmployeeAccountService;
import com.toy.utils.Message;
import com.toy.utils.PagingUtils;

@Transactional
@Service
public class EmployeeAccountServiceImp implements EmployeeAccountService {

	@Autowired
	EmployeeAccountRepository employeeAccountRepository;

	/**
	 * 根据员工编号模糊搜索分页查询员工账号
	 */
	@Override
	public Message<?> getAccountListByCode(String key, int page, int size) {
		Message<?> message = null;
		List<EmployeeAccount> employeeAccountList = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		employeeAccountList = employeeAccountRepository.findByEmployeeCodeLike("%" + key + "%", page1, size);
		int count = getCount(key);
		PagingUtils<?> paging = new PagingUtils<EmployeeAccount>(page, size, count, employeeAccountList);
		message = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return message;
	}

	/**
	 * 得到账号总数
	 */
	@Override
	public int getCount(String key) {
		int count = 0;
		count = employeeAccountRepository.getCount("%" + key + "%");
		return count;
	}

	/**
	 * 修改账号信息
	 */
	@Override
	public Message<?> update(EmployeeAccount empAccount) {
		Message<?> msg = null;
		try {
			employeeAccountRepository.save(empAccount);
			msg = new Message<EmployeeAccount>(HttpStatus.OK, Message.MSG_SUCCESS, empAccount);
		} catch (Exception e) {
			msg = new Message<EmployeeAccount>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return msg;
	}

	/**
	 * 根据员工编号删除账号
	 */
	@Override
	public Message<?> del(long accountId) {
		Message<?> msg = null;
		try {
			employeeAccountRepository.delete(accountId);
			msg = new Message<EmployeeAccount>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} catch (Exception e) {
			msg = new Message<EmployeeAccount>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return msg;
	}

	/**
	 * 停封与解封员工账号
	 */
	@Override
	public Message<?> modifyState(long accountId) {
		Message<?> msg = null;
		EmployeeAccount empAccount = null;
		try {
			empAccount = employeeAccountRepository.findOne(accountId);
			if (null != empAccount) {
				empAccount.setEmployeeAvailable("1".equals(empAccount.getEmployeeAvailable()) ? "0" : "1");
			}
			employeeAccountRepository.save(empAccount);
			msg = new Message<String>(HttpStatus.OK, Message.MSG_SUCCESS, empAccount.getEmployeeAvailable());
		} catch (Exception e) {
			msg = new Message<EmployeeAccount>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return msg;
	}
}
