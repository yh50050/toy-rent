package com.toy.service.imp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.model.Member;
import com.toy.model.MemberRecharge;
import com.toy.repository.MemberRechargeRepository;
import com.toy.repository.MemberRepository;
import com.toy.service.MemberService;
import com.toy.utils.Message;
import com.toy.utils.PagingUtils;

@Transactional
@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberRechargeRepository memberRechargeRepository;

	/**
	 * 新增或修改一名会员
	 * @throws Exception 
	 */
	@Override
	public Message<?> save(Member member) throws Exception {
		Message<?> msg = null;
		try {
			memberRepository.save(member);
			msg = new Message<Member>(HttpStatus.OK, Message.MSG_SUCCESS, member);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	/**
	 * 根据会员姓名、电话或者编号来匹配
	 */
	@Override
	public Message<?> findByNameOrPhoneOrCode(String key, int page, int size) {
		Message<?> message = null;
		List<Member> members = null;
		int page1 = 0;
		if (page > 0) {
			page1 = (page - 1) * size;
		} else {
			page = 1;
		}
		members = memberRepository.findByMemberNameOrMemberPhoneOrMemberCode("%" + key + "%", page1, size);
		int count = getCount(key);
		PagingUtils<?> paging = new PagingUtils<Member>(page, size, count, members);
		message = new Message<PagingUtils<?>>(HttpStatus.OK, Message.MSG_SUCCESS, paging);
		return message;
	}

	/**
	 * 根据id删除一名会员
	 * @throws Exception 
	 */
	@Override
	public Message<?> delete(long memberId) throws Exception {
		Message<?> msg = null;
		try {
			memberRepository.delete(memberId);
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	/**
	 * 得到会员的数量
	 */
	@Override
	public int getCount(String key) {
		int count = 0;
		count = memberRepository.getCount("%" + key + "%");
		return count;
	}

	/**
	 * 通过手机号得到会员
	 * @throws Exception 
	 */
	@Override
	public Message<?> getByPhone(String phone) throws Exception {
		Message<?> msg = null;
		Member member = null;
		try {
			member = memberRepository.findByMemberPhone(phone);
			if (null != member)
				msg = new Message<Object>(HttpStatus.OK, Message.MSG_SUCCESS, null);
			else
				msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	/**
	 * 通过编号得到会员
	 * @throws Exception 
	 */
	@Override
	public Message<?> getByCode(String code) throws Exception {
		Message<?> msg = null;
		Member member = null;
		try {
			member = memberRepository.findByMemberCode(code);
			if (null != member)
				msg = new Message<Object>(HttpStatus.OK, Message.MSG_SUCCESS, null);
			else
				msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	/**
	 * 通过id得到会员
	 * @throws Exception 
	 */
	@Override
	public Message<?> getById(long memberId) throws Exception {
		Message<?> msg = null;
		Member member = null;
		try {
			member = memberRepository.findOne(memberId);
			if (null != member)
				msg = new Message<Member>(HttpStatus.OK, Message.MSG_SUCCESS, member);
			else
				msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	/**
	 * 通过手机号或者编号的到会员
	 * @throws Exception 
	 */
	@Override
	public Message<?> getByCodeOrPhone(String key) throws Exception {
		Message<?> msg = null;
		Member member = null;
		try {
			member = memberRepository.findByMemberCodeOrMemberPhone(key);
			if (null != member)
				msg = new Message<Member>(HttpStatus.OK, Message.MSG_SUCCESS, member);
			else
				msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	/**
	 * 会员充值
	 * @throws Exception 
	 */
	@Override
	public Message<?> recharge(String memberCode, Integer money) throws Exception {
		Message<?> msg = null;
		Member member = null;
		try {
			member = memberRepository.findByMemberCodeOrMemberPhone(memberCode);
			if (null != member) {
				member.setMemberIntegral(member.getMemberIntegral() + money);
				memberRepository.save(member);
				MemberRecharge mre = new MemberRecharge();
				String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				mre.setMrDateTime(time);
				mre.setMrMemberId(member.getMemberId());
				mre.setMrMoney(new BigDecimal(money));
				memberRechargeRepository.save(mre);
				msg = new Message<Member>(HttpStatus.OK, Message.MSG_SUCCESS, member);
			} else
				msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

	/**
	 * 得到充值记录
	 * @throws Exception 
	 */
	@Override
	public Message<?> getRecord(long memberId) throws Exception {
		Message<?> msg = null;
		List<MemberRecharge> mrRecharges = null;
		try {
			mrRecharges = memberRechargeRepository.findByMrMemberId(memberId);
			if (null != mrRecharges)
				msg = new Message<List<MemberRecharge>>(HttpStatus.OK, Message.MSG_SUCCESS, mrRecharges);
			else
				msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
		} catch (Exception e) {
			msg = new Message<Object>(HttpStatus.OK, Message.MSG_FAIL, null);
			throw e;
		}
		return msg;
	}

}
