package com.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.toy.model.Toy;
import com.toy.service.ToyService;
import com.toy.utils.Message;

/**
 * 玩具操作接口
 * 
 * @author 枫茗丿love
 *
 */
@Controller
@RequestMapping("/toy")
public class ToyController {

	@Autowired
	ToyService toyService;

	/**
	 * 模糊匹配得到已上架玩具列表
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
		message = toyService.getListByNameOrType(key, "1", page, size);
		model.addAttribute("message", message);
		model.addAttribute("key", key);
		return "toy";
	}

	/**
	 * 根据id得到玩具信息
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> get(ModelAndView mv, long toyId) {
		Message<?> message = null;
		message = toyService.getById(toyId);
		return message;
	}

	/**
	 * 修改玩具信息
	 * 
	 * @param employee
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Toy toy, ModelAndView mv, String key, Integer page, Integer size) throws Exception {
		Message<?> message = null;
		message = toyService.save(toy);
		mv.addObject("update", message.getMessage());
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.setViewName("redirect:/toy/getList");
		return mv;
	}

	/**
	 * 模糊匹配得到所有玩具列表
	 * 
	 * @param model
	 * @param key
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/getAllList", method = RequestMethod.GET)
	public String getAllList(Model model, String key, Integer page, Integer size) {
		if (null == key)
			key = "";
		if (null == page)
			page = 0;
		if (null == size)
			size = 10;
		Message<?> message = null;
		message = toyService.getListByNameOrType(key, "%", page, size);
		model.addAttribute("message", message);
		model.addAttribute("key", key);
		return "up-down-toy";
	}

	/**
	 * 添加玩具
	 * 
	 * @param toy
	 * @param mv
	 * @param key
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(Toy toy, ModelAndView mv, String key, Integer page, Integer size) throws Exception {
		Message<?> message = null;
		message = toyService.save(toy);
		mv.addObject("add", message.getMessage());
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.setViewName("redirect:/toy/getAllList");
		return mv;
	}

	/**
	 * 上架或下架玩具
	 * 
	 * @param toyId
	 * @return
	 */
	@RequestMapping(value = "/up-down", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> on_Off(String toyId) {
		Message<?> message = null;
		message = toyService.modifyState(Long.parseLong(toyId));
		return message;
	}

	/**
	 * 删除玩具
	 * 
	 * @param toyId
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView del(long toyId, ModelAndView mv, String key, Integer page, Integer size) {
		Message<?> message = null;
		message = toyService.deleteById(toyId);
		mv.addObject("del", message.getMessage());
		mv.addObject("key", key);
		mv.addObject("page", page);
		mv.addObject("size", size);
		mv.setViewName("redirect:/toy/getAllList");
		return mv;
	}

	/**
	 * 出租玩具
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toyRent", method = RequestMethod.POST)
	@ResponseBody
	public Message<?> toyRent() {
		Message<?> message = null;
		return message;
	}
}
