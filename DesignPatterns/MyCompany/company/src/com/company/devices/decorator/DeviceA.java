package com.company.devices.decorator;

import java.util.ArrayList;

import com.company.bugs.BugInterface;
import com.company.devices.Device;
import com.company.devices.DeviceInterface;
import com.company.documentation.DocumentationInterface;

public class DeviceA extends Device {

	DeviceInterface device;
	
	public DeviceA(DeviceInterface device){
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
		System.out.println("****************** DEVICE A ********************\n");
		device.printDevice();
	}

}
