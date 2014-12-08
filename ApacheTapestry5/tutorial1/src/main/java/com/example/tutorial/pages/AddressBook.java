package com.example.tutorial.pages;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.example.tutorial.entities.Address;

public class AddressBook
{

	@Inject
	private Session session;
	 
	public List<Address> getAddresses()
	{
	    return session.createCriteria(Address.class).list();
	}
}
