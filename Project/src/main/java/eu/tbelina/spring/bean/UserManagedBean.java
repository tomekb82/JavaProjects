package eu.tbelina.spring.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import eu.tbelina.spring.model.User;
import eu.tbelina.spring.service.IUserService;

/**
 * 
 * User Managed Bean
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 * 
 */
@ManagedBean(name = "userMB")
@SessionScoped
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	// Spring User Service is injected...
	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	List<User> userList;

	private int id;
	private String name;
	private String surname;
	private String cardId;

	
	public String addUser() {
		try {
			User user = new User();
			user.setId(getId());
			user.setName(getName());
			user.setSurname(getSurname());
			getUserService().addUser(user);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return ERROR;
	}
	
	public void getUserByCardId() {
		User user = userService.getUserByCardId(getCardId());
		setName(user.getName());
		setSurname(user.getSurname());
		setCardId("");
	}

	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setId(0);
		this.setName("");
		this.setSurname("");
		this.setCardId("");
	}

	
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}

	
	public IUserService getUserService() {
		return userService;
	}

	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set User Name
	 * 
	 * @param String
	 *            - User Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get User Surname
	 * 
	 * @return String - User Surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set User Surname
	 * 
	 * @param String
	 *            - User Surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

}