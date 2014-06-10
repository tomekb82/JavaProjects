/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.qualent.telecom.admin.service.impl;

import java.util.List;
import java.util.logging.Logger;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import eu.qualent.telecom.admin.bean.AdminManagedBean;
import eu.qualent.telecom.admin.service.base.User3ULocalServiceBaseImpl;

/**
 * The implementation of the user3 u local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.qualent.telecom.admin.service.User3ULocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author belina
 * @see eu.qualent.telecom.admin.service.base.User3ULocalServiceBaseImpl
 * @see eu.qualent.telecom.admin.service.User3ULocalServiceUtil
 */
public class User3ULocalServiceImpl extends User3ULocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link eu.qualent.telecom.admin.service.User3ULocalServiceUtil} to access the user3 u local service.
	 */
	
	 private final static Logger LOGGER = Logger.getLogger(AdminManagedBean.class.getName());
	 
	 private List<User> userList;
     private Group userGroup;
     int userCount;
  

     /* 
      * Get all users 
      */  
     public List<User> getUserList(){
    	 
    	 try { 
    		 //userCount = UserLocalServiceUtil.getUsersCount();
    		 //userList = UserLocalServiceUtil.getUsers(0, userCount);
    		 userList = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    		 for (User user:userList) {
    			 LOGGER.info("User: " + user.getFullName());
             }
    	 } catch (SystemException e) {
                     // TODO Auto-generated catch block
    		 	e.printStackTrace();
    	 }
    	 return userList;
     }
}