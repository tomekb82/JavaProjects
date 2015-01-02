package com.tbelina.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbelina.spring.dao.UserDao;
import com.tbelina.spring.model.User;

@Service
public class UsersService {

	@Autowired
	UserDao usersDao;
	
	public void deleteUser(User user) {
		deleteUser(user.getId());
	}
	
	public void deleteUser(Integer id) {
		User user = usersDao.findById(id);
		user.setActive(false);
		usersDao.update(user);
	}
	
}
