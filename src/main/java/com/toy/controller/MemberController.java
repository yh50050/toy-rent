package com.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.toy.model.Member;
import com.toy.service.MemberService;
import com.toy.utils.Message;

/**
 * 会员操作接口
 * 
 * @author 枫茗丿love
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	/**
	 * 通过关键字匹配
	 * 
	 * @param model
	 * @param key
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public String getList(Model model, String key, Integer page, Integer size) {
		if (null == key)
			key = "";
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		Message<?> message = null;
		message = memberService.findByNameOrPhoneOrCode(key, page, size);
		model.addAttribute("message", message);
		model.addAttribute("key", key);
		return "member";
	}

	/**
	 * 添加一名会员
	 * 
	 * @param member
	 * @param mv
	 * @param key
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(Member member, ModelAndView mv, String key, Integer page, Integer size) throws Exception {
		Message<?> message = null;
		message = memberService.save(member);
		mv.addObject("add", message.getMessage());
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.setViewName("redirect:/member/getList");
		return mv;
	}

	/**
	 * 通过id得到会员
	 * 
	 * @param mv
	 * @param memberId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> get(ModelAndView mv, long memberId) throws Exception {
		Message<?> message = null;
		message = memberService.getById(memberId);
		return message;
	}

	/**
	 * 通过电话得到会员
	 * 
	 * @param mv
	 * @param phone
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getByPhone", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> getByPhone(ModelAndView mv, String phone) throws Exception {
		Message<?> message = null;
		message = memberService.getByPhone(phone);
		return message;
	}

	/**
	 * 通过编号得到会员
	 * 
	 * @param mv
	 * @param code
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getByCode", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> getByCode(ModelAndView mv, String code) throws Exception {
		Message<?> message = null;
		message = memberService.getByCode(code);
		return message;
	}

	/**
	 * 修改会员信息
	 * 
	 * @param member
	 * @param mv
	 * @param key
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Member member, ModelAndView mv, String key, Integer page, Integer size)
			throws Exception {
		Message<?> message = null;
		message = memberService.save(member);
		mv.addObject("update", message.getMessage());
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.setViewName("redirect:/member/getList");
		return mv;
	}

	/**
	 * 根据id删除会员
	 * 
	 * @param memberId
	 * @param mv
	 * @param key
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView del(long memberId, ModelAndView mv, String key, Integer page, Integer size) throws Exception {
		Message<?> message = null;
		message = memberService.delete(memberId);
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.addObject("del", message.getMessage());
		mv.setViewName("redirect:/member/getList");
		return mv;
	}

	/**
	 * 通过手机号或者编号得到会员
	 * 
	 * @param mv
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getByCodeOrPhone", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> getByCodeOrPhone(ModelAndView mv, String key) throws Exception {
		Message<?> message = null;
		message = memberService.getByCodeOrPhone(key);
		return message;
	}

	/**
	 * 会员充值
	 * 
	 * @param memberCode
	 * @param money
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> recharge(String memberCode, Integer money) throws Exception {
		Message<?> message = null;
		message = memberService.recharge(memberCode, money);
		return message;
	}

	/**
	 * 得到充值记录
	 * 
	 * @param memberId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> record(long memberId) throws Exception {
		Message<?> message = null;
		message = memberService.getRecord(memberId);
		return message;
	}
}
