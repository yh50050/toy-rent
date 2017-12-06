package com.toy.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.model.Employee;
import com.toy.model.EmployeeAccount;
import com.toy.repository.EmployeeAccountRepository;
import com.toy.repository.EmployeeRepository;
import com.toy.service.EmployeeService;
import com.toy.utils.Message;
import com.toy.utils.PagingUtils;

@Transactional
@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeAccountRepository employeeAccountRepository;

	/**
	 * 验证员工登陆
	 */
	@Override
	public Message<?> login(String employeeCode, String empoyeePassword) {
		Message<?> message = null;
		EmployeeAccount employeeAccount = employeeAccountRepository.findByEmployeeCodeAndEmpoyeePassword(employeeCode,
				empoyeePassword);
		if (null != employeeAccount) {
			if("1".equals(employeeAccount.getEmployeeAvailable())) {
				Employee employee = employeeRepository.findByEmployeeCode(employeeCode);
				message = new Message<Employee>(HttpStatus.OK, Message.MSG_SUCCESS, employee);
			}else {
				message = new Message<Employee>(HttpStatus.OK, "no", null);
			}
		} else {
			message = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return message;
	}

	/**
	 * 新增一名员工并注册一个员工账号，默认密码6个0
	 * 
	 * @throws Exception
	 */
	@Override
	public Message<?> save(Employee employee) throws Exception {
		Message<?> message = null;
		try {
			EmployeeAccount empAccount = new EmployeeAccount();
			empAccount.setEmployeeCode(employee.getEmployeeCode());
			empAccount.setEmpoyeePassword("000000");
			empAccount.setEmployeeAvailable("1");
			employeeRepository.save(employee);
			employeeAccountRepository.save(empAccount);
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} catch (Exception e) {
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return message;
	}

	/**
	 * 根据id删除一名员工和账号
	 * 
	 * @throws Exception
	 */
	@Override
	public Message<?> delete(long employeeId, String code) throws Exception {
		Message<?> message = null;
		try {
			employeeRepository.delete(employeeId);
			employeeAccountRepository.deleteByEmployeeCode(code);
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} catch (Exception e) {
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return message;
	}

	/**
	 * 根据员工姓名或编号模糊匹配
	 */
	@Override
	public Message<?> getListByNameOrCode(String key, int page, int size) {
		Message<?> message = null;
		List<Employee> employeeList = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		employeeList = employeeRepository.findByEmployeeNameLikeOrEmployeeCodeLike("%" + key + "%", page1, size);
		int count = getCount(key);
		PagingUtils<?> paging = new PagingUtils<Employee>(page, size, count, employeeList);
		message = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return message;
	}

	/**
	 * 得到员工的总数
	 */
	@Override
	public int getCount(String key) {
		int count = 0;
		count = employeeRepository.getCount("%" + key + "%");
		return count;
	}

	/**
	 * 根据员工id得到员工信息
	 */
	@Override
	public Message<?> getById(long employeeId) {
		Message<?> message = null;
		Employee employee = null;
		employee = employeeRepository.findOne(employeeId);
		if (null != employee)
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_SUCCESS, employee);
		else
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_FAIL, null);
		return message;
	}

	/**
	 * 更新信息
	 */
	@Override
	public Message<?> update(Employee employee) throws Exception {
		Message<?> message = null;
		try {
			EmployeeAccount empAccount = null;
			empAccount = employeeAccountRepository.findByEmployeeCode(employee.getEmployeeCode());
			if (null == empAccount) {
				empAccount = new EmployeeAccount();
				empAccount.setEmpoyeePassword("000000");
				empAccount.setEmployeeAvailable("1");
			}
			empAccount.setEmployeeCode(employee.getEmployeeCode());
			employeeRepository.save(employee);
			employeeAccountRepository.save(empAccount);
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} catch (

		Exception e) {
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return message;
	}

	/**
	 * 验证员工编号是否被使用
	 */
	@Override
	public Message<?> checkEmployeeCode(String code) {
		Message<?> message = null;
		Employee employee = null;
		employee = employeeRepository.findByEmployeeCode(code);
		if (null != employee) {
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} else {
			message = new Message<Employee>(HttpStatus.OK, Message.MSG_FAIL, null);
		}
		return message;
	}

}
