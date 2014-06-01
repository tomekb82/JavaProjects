package com.proxypattern.rmi_helloworld;

import java.rmi.*;

public interface MyRemote extends Remote{

	public String sayHello() throws RemoteException;
	
}
