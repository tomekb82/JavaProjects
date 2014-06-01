package com.proxypattern.person_bean;

import java.lang.reflect.Proxy;

public class MainApp {

	public static void main(String[] args){
		MainApp test = new MainApp();
		test.drive();
	}
	
	public MainApp(){
		//initializeDatabase();
	}
	
	public void drive(){
		
		PersonBean joe = new PersonBeanImpl("Joe Javabean");//getPersonFromDatabase("Joe Javabean");
		
		PersonBean ownerProxy = getOwnerProxy(joe);
		System.out.println("Name is " + ownerProxy.getName());
		ownerProxy.setInterests("bowling, Go");
		System.out.println("Interests set form owner proxy");
		try{
			ownerProxy.setHotOrNotRating(10);
		}catch (Exception e){
			System.out.println("Can't set rating from non owner proxy.");
		}
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());
		
		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("Name is " + nonOwnerProxy.getName());
		try{
			nonOwnerProxy.setInterests("bowling, Go");
		}catch (Exception e){
			System.out.println("Can't set interests from non owner proxy.");
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println("Rating set from non owner proxy");
		System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
	}
	
	PersonBean getOwnerProxy(PersonBean person){
		return (PersonBean) Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new OwnerInvocationHandler(person));
	}
	
	PersonBean getNonOwnerProxy(PersonBean person){
		return (PersonBean) Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new NonOwnerInvocationHandler(person));
	}
}
