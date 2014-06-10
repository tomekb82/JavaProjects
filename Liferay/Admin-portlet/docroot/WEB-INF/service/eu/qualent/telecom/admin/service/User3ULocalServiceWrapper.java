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

package eu.qualent.telecom.admin.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link User3ULocalService}.
 *
 * @author belina
 * @see User3ULocalService
 * @generated
 */
public class User3ULocalServiceWrapper implements User3ULocalService,
	ServiceWrapper<User3ULocalService> {
	public User3ULocalServiceWrapper(User3ULocalService user3ULocalService) {
		_user3ULocalService = user3ULocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _user3ULocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_user3ULocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _user3ULocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public java.util.List<com.liferay.portal.model.User> getUserList() {
		return _user3ULocalService.getUserList();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public User3ULocalService getWrappedUser3ULocalService() {
		return _user3ULocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedUser3ULocalService(
		User3ULocalService user3ULocalService) {
		_user3ULocalService = user3ULocalService;
	}

	@Override
	public User3ULocalService getWrappedService() {
		return _user3ULocalService;
	}

	@Override
	public void setWrappedService(User3ULocalService user3ULocalService) {
		_user3ULocalService = user3ULocalService;
	}

	private User3ULocalService _user3ULocalService;
}