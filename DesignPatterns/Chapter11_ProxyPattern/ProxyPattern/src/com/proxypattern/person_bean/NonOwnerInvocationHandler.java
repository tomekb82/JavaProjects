package com.proxypattern.person_bean;

import java.lang.reflect.*;

public class NonOwnerInvocationHandler implements InvocationHandler{
	
	PersonBean person;
	
	public NonOwnerInvocationHandler(PersonBean person){
		this.person = person;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException{
		
		try{
			if(method.getName().startsWith("get")){
				return method.invoke(person, args);
			} else if(method.getName().startsWith("setHotOrNotRating")){
				return method.invoke(person, args);
			}else if(method.getName().startsWith("set")){
				throw new IllegalAccessException();
			}
		} catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return null;
	}
}