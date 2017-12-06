package com.toy.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(true);
		// 判断用户是否登录，不存在就跳转到登录界面
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (response.getStatus() == 500) {
			modelAndView.setViewName("500");
		} else if (response.getStatus() == 404) {
			modelAndView.setViewName("404");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 自动生成的方法存根

	}

}
