package com.company.devices.factory;

import com.company.devices.*;
import com.company.devices.decorator.DeviceA;
import com.company.devices.decorator.DeviceB;
import com.company.devices.decorator.DeviceC;
import com.company.devices.decorator.DeviceD;

public class DeviceFactory extends AbstractDeviceFactory {

	public DeviceInterface createDeviceA(){
		return new DeviceA(new Device());
	}
	public DeviceInterface createDeviceB(){
		return new DeviceB(new Device());
	}
	public DeviceInterface createDeviceC(){
		return new DeviceC(new Device());
	}
	public DeviceInterface createDeviceD(){
		return new DeviceD(new Device());
	}
}
