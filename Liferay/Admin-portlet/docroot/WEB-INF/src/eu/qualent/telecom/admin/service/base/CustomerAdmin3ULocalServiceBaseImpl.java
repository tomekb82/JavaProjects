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

package eu.qualent.telecom.admin.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.persistence.UserPersistence;

import eu.qualent.telecom.admin.service.CustomerAdmin3ULocalService;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the customer admin3 u local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link eu.qualent.telecom.admin.service.impl.CustomerAdmin3ULocalServiceImpl}.
 * </p>
 *
 * @author belina
 * @see eu.qualent.telecom.admin.service.impl.CustomerAdmin3ULocalServiceImpl
 * @see eu.qualent.telecom.admin.service.CustomerAdmin3ULocalServiceUtil
 * @generated
 */
public abstract class CustomerAdmin3ULocalServiceBaseImpl
	extends BaseLocalServiceImpl implements CustomerAdmin3ULocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link eu.qualent.telecom.admin.service.CustomerAdmin3ULocalServiceUtil} to access the customer admin3 u local service.
	 */

	/**
	 * Returns the customer admin3 u local service.
	 *
	 * @return the customer admin3 u local service
	 */
	public eu.qualent.telecom.admin.service.CustomerAdmin3ULocalService getCustomerAdmin3ULocalService() {
		return customerAdmin3ULocalService;
	}

	/**
	 * Sets the customer admin3 u local service.
	 *
	 * @param customerAdmin3ULocalService the customer admin3 u local service
	 */
	public void setCustomerAdmin3ULocalService(
		eu.qualent.telecom.admin.service.CustomerAdmin3ULocalService customerAdmin3ULocalService) {
		this.customerAdmin3ULocalService = customerAdmin3ULocalService;
	}

	/**
	 * Returns the customer admin3 u remote service.
	 *
	 * @return the customer admin3 u remote service
	 */
	public eu.qualent.telecom.admin.service.CustomerAdmin3UService getCustomerAdmin3UService() {
		return customerAdmin3UService;
	}

	/**
	 * Sets the customer admin3 u remote service.
	 *
	 * @param customerAdmin3UService the customer admin3 u remote service
	 */
	public void setCustomerAdmin3UService(
		eu.qualent.telecom.admin.service.CustomerAdmin3UService customerAdmin3UService) {
		this.customerAdmin3UService = customerAdmin3UService;
	}

	/**
	 * Returns the portal admin3 u local service.
	 *
	 * @return the portal admin3 u local service
	 */
	public eu.qualent.telecom.admin.service.PortalAdmin3ULocalService getPortalAdmin3ULocalService() {
		return portalAdmin3ULocalService;
	}

	/**
	 * Sets the portal admin3 u local service.
	 *
	 * @param portalAdmin3ULocalService the portal admin3 u local service
	 */
	public void setPortalAdmin3ULocalService(
		eu.qualent.telecom.admin.service.PortalAdmin3ULocalService portalAdmin3ULocalService) {
		this.portalAdmin3ULocalService = portalAdmin3ULocalService;
	}

	/**
	 * Returns the portal admin3 u remote service.
	 *
	 * @return the portal admin3 u remote service
	 */
	public eu.qualent.telecom.admin.service.PortalAdmin3UService getPortalAdmin3UService() {
		return portalAdmin3UService;
	}

	/**
	 * Sets the portal admin3 u remote service.
	 *
	 * @param portalAdmin3UService the portal admin3 u remote service
	 */
	public void setPortalAdmin3UService(
		eu.qualent.telecom.admin.service.PortalAdmin3UService portalAdmin3UService) {
		this.portalAdmin3UService = portalAdmin3UService;
	}

	/**
	 * Returns the user3 u local service.
	 *
	 * @return the user3 u local service
	 */
	public eu.qualent.telecom.admin.service.User3ULocalService getUser3ULocalService() {
		return user3ULocalService;
	}

	/**
	 * Sets the user3 u local service.
	 *
	 * @param user3ULocalService the user3 u local service
	 */
	public void setUser3ULocalService(
		eu.qualent.telecom.admin.service.User3ULocalService user3ULocalService) {
		this.user3ULocalService = user3ULocalService;
	}

	/**
	 * Returns the user3 u remote service.
	 *
	 * @return the user3 u remote service
	 */
	public eu.qualent.telecom.admin.service.User3UService getUser3UService() {
		return user3UService;
	}

	/**
	 * Sets the user3 u remote service.
	 *
	 * @param user3UService the user3 u remote service
	 */
	public void setUser3UService(
		eu.qualent.telecom.admin.service.User3UService user3UService) {
		this.user3UService = user3UService;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = eu.qualent.telecom.admin.service.CustomerAdmin3ULocalService.class)
	protected eu.qualent.telecom.admin.service.CustomerAdmin3ULocalService customerAdmin3ULocalService;
	@BeanReference(type = eu.qualent.telecom.admin.service.CustomerAdmin3UService.class)
	protected eu.qualent.telecom.admin.service.CustomerAdmin3UService customerAdmin3UService;
	@BeanReference(type = eu.qualent.telecom.admin.service.PortalAdmin3ULocalService.class)
	protected eu.qualent.telecom.admin.service.PortalAdmin3ULocalService portalAdmin3ULocalService;
	@BeanReference(type = eu.qualent.telecom.admin.service.PortalAdmin3UService.class)
	protected eu.qualent.telecom.admin.service.PortalAdmin3UService portalAdmin3UService;
	@BeanReference(type = eu.qualent.telecom.admin.service.User3ULocalService.class)
	protected eu.qualent.telecom.admin.service.User3ULocalService user3ULocalService;
	@BeanReference(type = eu.qualent.telecom.admin.service.User3UService.class)
	protected eu.qualent.telecom.admin.service.User3UService user3UService;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private CustomerAdmin3ULocalServiceClpInvoker _clpInvoker = new CustomerAdmin3ULocalServiceClpInvoker();
}