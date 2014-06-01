package com.company.devices.decorator;

import com.company.devices.Device;
import com.company.devices.DeviceInterface;

public class DeviceB extends Device{

	DeviceInterface device;
	
	public DeviceB(DeviceInterface device){
		this.device = device;
	}
	
	public void setSystem(String system){
		device.setSystem(system);
	}
	
	public void setModule(String module){
		device.setModule(module);
	}
	
	public void setAlgorithm(String algorithm){
		device.setAlgorithm(algorithm);
	}
	
	public void setVersion(String version){
		device.setVersion(version);
	}
	
	public void printDevice(){
		System.out.println("****************** DEVICE B ********************\n");
		device.printDevice();
	}
}
