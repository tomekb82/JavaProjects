package com.packtpub.as7development.chapter8.service;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
@ManagedBean
@ViewScoped
public class PollerBean implements Serializable{
	boolean pollingActive=true;



	public boolean isPollingActive() {
		return pollingActive;
	}
	public void setPollingActive(boolean pollingActive) {
		this.pollingActive = pollingActive;
	}
}
