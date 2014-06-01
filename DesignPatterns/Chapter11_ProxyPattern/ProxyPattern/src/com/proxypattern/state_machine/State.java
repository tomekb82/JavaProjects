package com.proxypattern.state_machine;

import java.io.*;

public interface State extends Serializable {

	public void gotoState1();
	
	public void gotoState2();
	
	public void gotoState3();

	public void gotoState4();
}
