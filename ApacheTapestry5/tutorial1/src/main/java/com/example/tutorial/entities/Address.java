package com.example.tutorial.entities;
 
import org.apache.tapestry5.beaneditor.Validate;

import com.example.tutorial.data.Honorific;
 
public class Address
{
    public Honorific honorific;
    @Validate("required")
    public String firstName;
    @Validate("required")
    public String lastName;
    @Validate("required")
    public String street1;
    @Validate("required")
    public String street2;
    @Validate("required")
    public String city;
    @Validate("required")
    public String state;
    @Validate("required,regexp")
    public String zip;
    @Validate("required")
    public String email;
    @Validate("required")
    public String phone;
}