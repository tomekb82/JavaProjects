package com.company.devices;

public class DeviceD extends Device{

	DeviceInterface device = new Device();
	
	public void printDevice(){
		System.out.println("****************** DEVICE D ********************\n");
		device.printDevice();
	}
}
