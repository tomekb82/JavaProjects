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
 * Provides a wrapper for {@link PortalAdmin3ULocalService}.
 *
 * @author belina
 * @see PortalAdmin3ULocalService
 * @generated
 */
public class PortalAdmin3ULocalServiceWrapper
	implements PortalAdmin3ULocalService,
		ServiceWrapper<PortalAdmin3ULocalService> {
	public PortalAdmin3ULocalServiceWrapper(
		PortalAdmin3ULocalService portalAdmin3ULocalService) {
		_portalAdmin3ULocalService = portalAdmin3ULocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _portalAdmin3ULocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_portalAdmin3ULocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _portalAdmin3ULocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PortalAdmin3ULocalService getWrappedPortalAdmin3ULocalService() {
		return _portalAdmin3ULocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPortalAdmin3ULocalService(
		PortalAdmin3ULocalService portalAdmin3ULocalService) {
		_portalAdmin3ULocalService = portalAdmin3ULocalService;
	}

	@Override
	public PortalAdmin3ULocalService getWrappedService() {
		return _portalAdmin3ULocalService;
	}

	@Override
	public void setWrappedService(
		PortalAdmin3ULocalService portalAdmin3ULocalService) {
		_portalAdmin3ULocalService = portalAdmin3ULocalService;
	}

	private PortalAdmin3ULocalService _portalAdmin3ULocalService;
}