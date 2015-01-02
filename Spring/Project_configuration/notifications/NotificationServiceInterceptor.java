package com.tbelina.notifications;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

public class NotificationServiceInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	NotificationsService notificationService;
	
	
	private Logger logger;
	private final String _attributeName_notifications = "notifications";
	
	public NotificationServiceInterceptor() {
		logger = Logger.getLogger(this.getClass().getName());
		logger.info("NotificationServiceInterceptor initialized");
    }
	
	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && !(modelAndView.getView() instanceof RedirectView)
				&& !modelAndView.getViewName().startsWith("redirect:")) {
			if (modelAndView != null && modelAndView.getModelMap() != null) {
				modelAndView.getModelMap().addAttribute(_attributeName_notifications, notificationService.getNotifications());
				notificationService.clearNotifications();
			}
		}
		
	}

}
