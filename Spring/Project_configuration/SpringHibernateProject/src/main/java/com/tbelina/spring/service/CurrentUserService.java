package com.tbelina.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tbelina.spring.dao.UserDao;
import com.tbelina.spring.model.User;


@Service
@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CurrentUserService {

	private  User user = null;
	
	@Autowired
	UserDao usersDao;
	
	public String getLogin() {
		return (user != null) ? user.getLogin() : ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
	
	public User getUser() {
		if (user == null) {
			user = usersDao.findByLogin(getLogin());
		}
		return user;
	}
	
	public void invalidateUserDetails() {
		user = null;
	}

}
