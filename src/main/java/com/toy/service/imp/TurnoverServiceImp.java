package com.toy.service.imp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.model.Turnover;
import com.toy.repository.TurnoverRepository;
import com.toy.service.TurnoverService;
import com.toy.utils.Message;
import com.toy.utils.PagingUtils;

@Transactional
@Service
public class TurnoverServiceImp implements TurnoverService {

	@Autowired
	TurnoverRepository turnoverRepository;

	@Override
	public Message<?> getListByDate() {
		Message<?> msg = null;
		LocalDate time = LocalDate.now();
		time = time.plusDays(-8);
		String date = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Turnover turnover = null;
		turnover = turnoverRepository.findByTurnoverDate(date);
		List<Turnover> turnovers = null;
		if (null != turnover) {
			turnovers = turnoverRepository.getList(turnover.getTurnoverId());
		}
		Collections.reverse(turnovers);
		msg = new Message<List<Turnover>>(HttpStatus.OK, Message.MSG_SUCCESS, turnovers);
		return msg;
	}

	@Override
	public Message<?> getAllList(int page, int size) {
		Message<?> msg = null;
		List<Turnover> turnovers = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		turnovers = turnoverRepository.getAllList(page1, size);
		int count = getCount();
		PagingUtils<?> paging = new PagingUtils<Turnover>(page, size, count, turnovers);
		msg = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return msg;
	}

	@Override
	public int getCount() {
		int result = 0;
		result = (int) turnoverRepository.count();
		return result;
	}

}
