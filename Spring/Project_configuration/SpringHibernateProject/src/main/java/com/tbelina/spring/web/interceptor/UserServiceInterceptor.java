package com.tbelina.spring.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.tbelina.spring.dao.UserDao;
import com.tbelina.spring.model.User;
	
public class UserServiceInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	UserDao userDao;
	
	private final String _attributeName_user = "logged_user_details";
	
	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && !(modelAndView.getView() instanceof RedirectView)
				&& !modelAndView.getViewName().startsWith("redirect:")) {
			if (modelAndView != null && modelAndView.getModelMap() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
				HttpSession session = request.getSession();
				if (session.getAttribute(_attributeName_user)==null) {
					String username = ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
					User user = userDao.findByLogin(username);
					session.setAttribute(_attributeName_user, user);
				}
				modelAndView.getModelMap().addAttribute(_attributeName_user, session.getAttribute(_attributeName_user));
			}
		}
	}
	
}
