package eu.qualent.telecom.admin.service;

import java.util.List;

import com.liferay.portal.model.User;

public interface IServiceAdmin {

	public List<User> getUsers();
	public User addUser(String userName, String firstName, String lastName, String passwd);
	public User delUser(long userId);
	public User updateUser();	
	public int getUserCount();
	public User getUserById(long userId);
	
}
