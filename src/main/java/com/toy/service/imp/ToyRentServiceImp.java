package com.toy.service.imp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.model.Admin;
import com.toy.model.Employee;
import com.toy.model.Member;
import com.toy.model.Toy;
import com.toy.model.ToyRent;
import com.toy.model.Turnover;
import com.toy.repository.AdminRepository;
import com.toy.repository.EmployeeRepository;
import com.toy.repository.MemberRepository;
import com.toy.repository.ToyRentRepository;
import com.toy.repository.ToyRepository;
import com.toy.repository.TurnoverRepository;
import com.toy.service.ToyRentService;
import com.toy.utils.CountDaysUtils;
import com.toy.utils.Message;
import com.toy.utils.PagingUtils;

@Transactional
@Service
public class ToyRentServiceImp implements ToyRentService {

	@Autowired
	ToyRepository toyRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	ToyRentRepository toyRentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	TurnoverRepository turnoverRepository;

	@Override
	public Message<?> save(ToyRent toyRent) throws Exception {
		Message<?> msg = null;
		try {
			String time = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			toyRent.setIsReturn("0");
			toyRent.setTrLaseTime(time);
			toyRentRepository.save(toyRent);

			Toy toy = toyRepository.findOne(toyRent.getTrToyId());
			toy.setToyStock(toy.getToyStock() - 1);
			toy.setToyRentAmount(toy.getToyRentAmount() + 1);
			toyRepository.save(toy);

			Member member = memberRepository.findOne(toyRent.getTrMemberId());
			member.setMemberIntegral(member.getMemberIntegral() - toy.getToyNeedPoint());
			memberRepository.save(member);

			Turnover turnover = null;
			turnover = turnoverRepository.findByTurnoverDate(time);
			if (null == turnover) {
				turnover = new Turnover();
				turnover.setTurnoverDate(time);
				turnover.setTurnoverHousesrented(1);
				turnover.setTurnoverMoney(new BigDecimal(0));
			} else {
				turnover.setTurnoverHousesrented(turnover.getTurnoverHousesrented() + 1);
			}
			turnoverRepository.save(turnover);

			msg = new Message<Object>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	@Override
	public Message<?> getIsReturnList(String isReturn, int page, int size) {
		Message<?> msg = null;
		List<ToyRent> toyRents = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		toyRents = toyRentRepository.getIsReturnList(isReturn, page1, size);
		getMemberAndToy(toyRents);
		int count = getIsReturnCount(isReturn);
		PagingUtils<?> paging = new PagingUtils<ToyRent>(page, size, count, toyRents);
		msg = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return msg;
	}

	@Override
	public Message<?> getMemberIsReturnList(String isReturn, long memberId, int page, int size) {
		Message<?> msg = null;
		List<ToyRent> toyRents = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		toyRents = toyRentRepository.getMemberIsReturnList(isReturn, memberId, page1, size);
		getMemberAndToy(toyRents);
		int count = getMemberIsReturnCount(isReturn, memberId);
		PagingUtils<?> paging = new PagingUtils<ToyRent>(page, size, count, toyRents);
		msg = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return msg;
	}

	@Override
	public Message<?> getMemberAllList(long memberId, int page, int size) {
		Message<?> msg = null;
		List<ToyRent> toyRents = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		toyRents = toyRentRepository.getMemberAllList(memberId, page1, size);
		getMemberAndToy(toyRents);
		int count = getMemberListCount(memberId);
		PagingUtils<?> paging = new PagingUtils<ToyRent>(page, size, count, toyRents);
		msg = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return msg;
	}

	@Override
	public Message<?> getAllList(int page, int size) {
		Message<?> msg = null;
		List<ToyRent> toyRents = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		toyRents = toyRentRepository.getAllList(page1, size);
		getMemberAndToy(toyRents);
		int count = getCount();
		PagingUtils<?> paging = new PagingUtils<ToyRent>(page, size, count, toyRents);
		msg = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return msg;
	}

	@Override
	public int getMemberIsReturnCount(String isReturn, long memberId) {
		int result = 0;
		result = toyRentRepository.getMemberIsReturn(isReturn, memberId);
		return result;
	}

	@Override
	public int getIsReturnCount(String isReturn) {
		int result = 0;
		result = toyRentRepository.getIsReturn(isReturn);
		return result;
	}

	@Override
	public int getCount() {
		long result = 0;
		result = toyRentRepository.count();
		return (int) result;
	}

	@Override
	public Message<?> getMemberRentList(long memberId, int page, int size) {
		Message<?> msg = null;
		List<ToyRent> toyRents = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		toyRents = toyRentRepository.getMemberRentList(memberId, page1, size);
		getMemberAndToy(toyRents);
		int count = getMemberRentCount(memberId);
		PagingUtils<?> paging = new PagingUtils<ToyRent>(page, size, count, toyRents);
		msg = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return msg;
	}

	@Override
	public Message<?> getById(long toyRentId) {
		Message<?> msg = null;
		ToyRent toy = toyRentRepository.findOne(toyRentId);
		msg = new Message<ToyRent>(HttpStatus.OK, Message.MSG_SUCCESS, toy);
		return msg;
	}

	@Override
	public Message<?> update(ToyRent toRent) {
		Message<?> msg = null;
		toyRentRepository.save(toRent);
		msg = new Message<Object>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		return msg;
	}

	@Override
	public int getMemberRentCount(long memberId) {
		int result = 0;
		result = toyRentRepository.getMemberRentCount(memberId);
		return result;
	}

	@Override
	public Message<?> returnToy(long trId) throws Exception {
		Message<?> msg = null;
		try {
			ToyRent toyRent = toyRentRepository.findOne(trId);
			Toy toy = null;
			Member member = null;
			long memberId = toyRent.getTrMemberId();
			long toyId = toyRent.getTrToyId();
			toy = toyRepository.findOne(toyId);
			member = memberRepository.findOne(memberId);
			if (null != toy) {
				toy.setToyStock(toy.getToyStock() + 1);
				toy.setToyRentAmount(toy.getToyRentAmount() - 1);
				toyRepository.save(toy);
			}
			if (null != member) {
				LocalDate laseDate = LocalDate.parse(toyRent.getTrLaseTime());
				LocalDate today = LocalDate.now();
				int days = CountDaysUtils.getBetweenDays(laseDate, today);
				if (days > toyRent.getTrDay()) {
					member.setMemberIntegral(member.getMemberIntegral() + toy.getToyNeedPoint() - 20);
				} else {
					member.setMemberIntegral(member.getMemberIntegral() + toy.getToyNeedPoint());
				}
				memberRepository.save(member);
			}
			toyRent.setIsReturn("1");
			String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			toyRent.setTrRturnTime(date);
			toyRentRepository.save(toyRent);

			Turnover turnover = null;
			turnover = turnoverRepository.findByTurnoverDate(date);
			if (null == turnover) {
				turnover = new Turnover();
				turnover.setTurnoverDate(date);
				turnover.setTurnoverHousesrented(0);
				turnover.setTurnoverMoney(toyRent.getMoney());
			} else {
				turnover.setTurnoverMoney(turnover.getTurnoverMoney().add(toyRent.getMoney()));
			}
			turnoverRepository.save(turnover);

			msg = new Message<Object>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	public void getMemberAndToy(List<ToyRent> toyRents) {
		LocalDate today = LocalDate.now();
		for (ToyRent toyRent : toyRents) {
			Member member = memberRepository.findOne(toyRent.getTrMemberId());
			toyRent.setMember(member);
			Toy toy = toyRepository.findOne(toyRent.getTrToyId());
			toyRent.setToy(toy);
			if ("0".equals(toyRent.getTrEmployeeType())) {
				Employee employee = employeeRepository.findOne(toyRent.getTrEmpolyeeId());
				toyRent.setPerson(employee);
			} else {
				Admin admin = adminRepository.findOne(toyRent.getTrEmpolyeeId());
				toyRent.setPerson(admin);
			}

			LocalDate laseDate = LocalDate.parse(toyRent.getTrLaseTime());
			int days = 0;
			if ("1".equals(toyRent.getIsReturn())) {
				LocalDate returnDate = LocalDate.parse(toyRent.getTrRturnTime());
				days = CountDaysUtils.getBetweenDays(laseDate, returnDate);
			} else {
				days = CountDaysUtils.getBetweenDays(laseDate, today);
			}
			toyRent.setDays(days + 1);
			if (days > toyRent.getTrDay()) {
				toyRent.setIsOverdue(1);
			}
			BigDecimal money = new BigDecimal("0");
			BigDecimal dailyRent = toy.getToyDailyRent();
			if (toyRent.getDays() > toyRent.getTrDay()) {
				BigDecimal beyondRent = toy.getToyBeyondRent();
				money = dailyRent.multiply(BigDecimal.valueOf(toyRent.getTrDay()))
						.add(beyondRent.multiply(BigDecimal.valueOf(toyRent.getDays() - toyRent.getTrDay())));
			} else {
				money = dailyRent.multiply(BigDecimal.valueOf(toyRent.getDays()));
			}
			toyRent.setMoney(money);
		}
	}

	@Override
	public int getMemberListCount(long memberId) {
		int result = 0;
		result = toyRentRepository.getMemberRentCount(memberId);
		return result;
	}

}
