package com.packtpub.as7development.chapter8.webservice;

import javax.jws.WebMethod; 
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService; 
import javax.jws.soap.SOAPBinding; 
@WebService(targetNamespace = "http://www.packtpub.com/", 
serviceName = "CalculatePowerService") 
@SOAPBinding(style = SOAPBinding.Style.RPC) 
public class POJOWebService 
{ 
	@WebMethod
	@WebResult(name="result")   
	public double calculatePower(@WebParam(name="base")   
	double base, @WebParam(name="exponent") double exponent)
	{ 
		return Math.pow(base,exponent); 
	} 
} 
