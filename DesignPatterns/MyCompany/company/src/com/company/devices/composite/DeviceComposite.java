package com.company.devices.composite;

import java.util.ArrayList;
import java.util.Iterator;

import com.company.devices.DeviceInterface;

public class DeviceComposite implements DeviceInterface{

	ArrayList devices = new ArrayList();
	
	public void add(DeviceInterface device){
		devices.add(device);
	}
	
	public void printDevice(){
		Iterator iterator = devices.iterator();
		while(iterator.hasNext()){
			DeviceInterface device = (DeviceInterface)iterator.next();
			device.printDevice();
		}
	}
	
	public void setSystem(String system){
		Iterator iterator = devices.iterator();
		while(iterator.hasNext()){
			DeviceInterface device = (DeviceInterface)iterator.next();
			device.setSystem(system);
		}
	}
}
