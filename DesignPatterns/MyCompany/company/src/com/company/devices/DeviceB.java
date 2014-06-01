package com.company.devices;

public class DeviceB extends Device{

	DeviceInterface device = new Device();
	
	public void printDevice(){
		System.out.println("****************** DEVICE B ********************\n");
		device.printDevice();
	}
}
