package eu.tbelina.spring.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import eu.tbelina.spring.dao.IUserDAO;
import eu.tbelina.spring.model.User;
import eu.tbelina.spring.service.IUserService;

/**
 * 
 * User Service
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@Transactional(readOnly = true)
public class UserService implements IUserService {

	// UserDAO is injected...
	IUserDAO userDAO;
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	@Override
	public void addUser(User user) {
		System.out.println("service adduser");
		getUserDAO().addUser(user);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	@Override
	public void deleteUser(User user) {
		getUserDAO().deleteUser(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateUser(User user) {
		getUserDAO().updateUser(user);
	}
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	@Override
	public User getUserById(int id) {
		return getUserDAO().getUserById(id);
	}

	/**
	 * Get User List
	 * 
	 */
	@Override
	public List<User> getUsers() {	
		return getUserDAO().getUsers();
	}

	/**
	 * Get User DAO
	 * 
	 * @return IUserDAO - User DAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Set User DAO
	 * 
	 * @param IUserDAO - User DAO
	 */
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User getUserByCardId(String id) {
		User user = new User();
		if(id.equals("0003134133")) {
			user.setName("Jan");
			user.setSurname("Nowak");
			return user;
		} else if(id.equals("0003124199")) {
			user.setName("Paweł");
			user.setSurname("Kowalski");
			return user;
		} else if(id.equals("0003141444")) {
			user.setName("Kazimierz");
			user.setSurname("Wichura");
			return user;
		} else if(id.equals("0003142072")) {
			user.setName("Wiktor");
			user.setSurname("Pupek");
			return user;
		} else {
			return user;
		}
	}
}
