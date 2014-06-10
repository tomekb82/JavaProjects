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
 * Provides a wrapper for {@link CustomerAdmin3UService}.
 *
 * @author belina
 * @see CustomerAdmin3UService
 * @generated
 */
public class CustomerAdmin3UServiceWrapper implements CustomerAdmin3UService,
	ServiceWrapper<CustomerAdmin3UService> {
	public CustomerAdmin3UServiceWrapper(
		CustomerAdmin3UService customerAdmin3UService) {
		_customerAdmin3UService = customerAdmin3UService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _customerAdmin3UService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_customerAdmin3UService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _customerAdmin3UService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CustomerAdmin3UService getWrappedCustomerAdmin3UService() {
		return _customerAdmin3UService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCustomerAdmin3UService(
		CustomerAdmin3UService customerAdmin3UService) {
		_customerAdmin3UService = customerAdmin3UService;
	}

	@Override
	public CustomerAdmin3UService getWrappedService() {
		return _customerAdmin3UService;
	}

	@Override
	public void setWrappedService(CustomerAdmin3UService customerAdmin3UService) {
		_customerAdmin3UService = customerAdmin3UService;
	}

	private CustomerAdmin3UService _customerAdmin3UService;
}