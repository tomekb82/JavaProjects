package com.proxypattern.state_machine;

import java.rmi.*;

/* Remote interface */
public interface StateMachineRemote extends Remote{

	public String getLocation() throws RemoteException;
	public State getState() throws RemoteException; /* State should be Serializable */
	public void gotoState2() throws RemoteException; 

}
