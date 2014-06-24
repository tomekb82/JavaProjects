package eu.qualent.telecom.admin.service.impl;

import java.util.List;
import java.util.Locale;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.lang.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import eu.qualent.telecom.admin.service.IServiceAdmin;

public class ServiceAdmin implements IServiceAdmin{

	private long userId;
	private long companyId;
	private Locale locale;
	
	/* 
	 * Get users
	 * @see eu.qualent.telecom.admin.service.IServiceAdmin#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		List<User> userList = null;
		try { 
			userList = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	
	/*
	 * Add user
	 * @see eu.qualent.telecom.admin.service.IServiceAdmin#addUser()
	 */
	@Override
	public User addUser(String userName, 
						String firstName,
						String lastName,
						String passwd) {
		
		if(initContext() != 0)
			return null;
			
		boolean active = true;
		boolean autoScreenName = false;
		boolean sendEmail = true;
		boolean autoPassword = false;
		String password1 = passwd;
		String password2 = passwd;
		String screenName = userName;
		if (autoScreenName) {
			screenName = StringPool.BLANK;
		}
		String emailAddress = userName + "@liferay.com";
		long facebookId = 0;
		String openId = StringPool.BLANK;
		String middleName = StringPool.BLANK;
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = 1;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] groupIds = new long[] {};
		long[] organizationIds = new long[] {};
		long[] roleIds = new long[] {};

		long[] userGroupIds = new long[] {};
		ServiceContext serviceContext = new ServiceContext();
		User user = null;
		try {
			user = UserLocalServiceUtil.addUser(this.userId, this.companyId, autoPassword, password1, password2,
					autoScreenName, screenName, emailAddress, facebookId, openId, this.locale, firstName, middleName, lastName,
					prefixId, suffixId, male, birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds, organizationIds,
					roleIds, userGroupIds, sendEmail, serviceContext);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	/*
	 * Delete user
	 * @see eu.qualent.telecom.admin.service.IServiceAdmin#delUser()
	 */
	@Override
	public User delUser(long userId) {
		User user = null;
		try {
			user = UserLocalServiceUtil.deleteUser(userId);
	   	 } catch (PortalException e) {
	   		 // TODO Auto-generated catch block
	   	 	e.printStackTrace();
	   	 } catch (SystemException e) {
	   	 	// TODO Auto-generated catch block
	   	 	e.printStackTrace();
	   	 }
		return user; 
	}

	/*
	 * Update user
	 * @see eu.qualent.telecom.admin.service.IServiceAdmin#updateUser()
	 */
	@Override
	public User updateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Get user by id
	 * @see eu.qualent.telecom.admin.service.IServiceAdmin#getUserCount()
	 */
	@Override
	public User getUserById(long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* 
	 * Get user count
	 * @see eu.qualent.telecom.admin.service.IServiceAdmin#getUserCount()
	 */
	@Override
	public int getUserCount() {
		try {
			return UserLocalServiceUtil.getUsersCount();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * Initialize context
	 */
	public int initContext() {
		LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
		if(liferayFacesContext == null)
			return 1;
		
		this.userId = liferayFacesContext.getUser().getUserId();
		this.companyId = liferayFacesContext.getCompanyId();
		this.locale = liferayFacesContext.getLocale();
		
		return 0;
	}

	
}
