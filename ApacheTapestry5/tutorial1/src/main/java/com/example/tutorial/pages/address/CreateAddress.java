package com.example.tutorial.pages.address;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.example.tutorial.entities.Address;
import com.example.tutorial.pages.AddressBook;

public class CreateAddress {

	@Property
    private Address address;
 
    @Inject//inject a service into the annotated field;
    private Session session;
 
    @InjectPage
    private AddressBook addressBook;
 
    @CommitAfter // can be applied to any component method; if the method completes normally, the transaction will be committed 
    Object onSuccess()
    {
        session.persist(address);
 
        return addressBook;
    }
}
