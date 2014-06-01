package com.company.devices.decorator;

import com.company.devices.Device;
import com.company.devices.DeviceInterface;

public class DeviceC extends Device{

	DeviceInterface device;
	
	public DeviceC(DeviceInterface device){
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
		System.out.println("****************** DEVICE C ********************\n");
		device.printDevice();
	}
}
