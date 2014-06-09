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
 * Provides a wrapper for {@link CustomerAdmin3ULocalService}.
 *
 * @author belina
 * @see CustomerAdmin3ULocalService
 * @generated
 */
public class CustomerAdmin3ULocalServiceWrapper
	implements CustomerAdmin3ULocalService,
		ServiceWrapper<CustomerAdmin3ULocalService> {
	public CustomerAdmin3ULocalServiceWrapper(
		CustomerAdmin3ULocalService customerAdmin3ULocalService) {
		_customerAdmin3ULocalService = customerAdmin3ULocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _customerAdmin3ULocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_customerAdmin3ULocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _customerAdmin3ULocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CustomerAdmin3ULocalService getWrappedCustomerAdmin3ULocalService() {
		return _customerAdmin3ULocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCustomerAdmin3ULocalService(
		CustomerAdmin3ULocalService customerAdmin3ULocalService) {
		_customerAdmin3ULocalService = customerAdmin3ULocalService;
	}

	@Override
	public CustomerAdmin3ULocalService getWrappedService() {
		return _customerAdmin3ULocalService;
	}

	@Override
	public void setWrappedService(
		CustomerAdmin3ULocalService customerAdmin3ULocalService) {
		_customerAdmin3ULocalService = customerAdmin3ULocalService;
	}

	private CustomerAdmin3ULocalService _customerAdmin3ULocalService;
}