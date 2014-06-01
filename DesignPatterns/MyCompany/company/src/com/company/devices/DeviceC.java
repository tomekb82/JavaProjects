package com.company.devices;

public class DeviceC extends Device{

	DeviceInterface device = new Device();
	
	public void printDevice(){
		System.out.println("****************** DEVICE C ********************\n");
		device.printDevice();
	}
}
