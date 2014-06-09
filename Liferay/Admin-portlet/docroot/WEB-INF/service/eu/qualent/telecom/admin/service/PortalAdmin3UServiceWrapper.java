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
 * Provides a wrapper for {@link PortalAdmin3UService}.
 *
 * @author belina
 * @see PortalAdmin3UService
 * @generated
 */
public class PortalAdmin3UServiceWrapper implements PortalAdmin3UService,
	ServiceWrapper<PortalAdmin3UService> {
	public PortalAdmin3UServiceWrapper(
		PortalAdmin3UService portalAdmin3UService) {
		_portalAdmin3UService = portalAdmin3UService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _portalAdmin3UService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_portalAdmin3UService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _portalAdmin3UService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PortalAdmin3UService getWrappedPortalAdmin3UService() {
		return _portalAdmin3UService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPortalAdmin3UService(
		PortalAdmin3UService portalAdmin3UService) {
		_portalAdmin3UService = portalAdmin3UService;
	}

	@Override
	public PortalAdmin3UService getWrappedService() {
		return _portalAdmin3UService;
	}

	@Override
	public void setWrappedService(PortalAdmin3UService portalAdmin3UService) {
		_portalAdmin3UService = portalAdmin3UService;
	}

	private PortalAdmin3UService _portalAdmin3UService;
}