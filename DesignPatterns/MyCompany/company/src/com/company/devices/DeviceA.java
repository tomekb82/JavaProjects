package com.company.devices;

public class DeviceA extends Device {

	DeviceInterface device = new Device();
	
	public void printDevice(){
		System.out.println("****************** DEVICE A ********************\n");
		device.printDevice();
	}

}
