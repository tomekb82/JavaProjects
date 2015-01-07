/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package customer.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import customer.data.Address;
import customer.data.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;


public class CustomerClientXML {
    public static final Logger logger = Logger.getLogger(
                CustomerClientXML.class.getCanonicalName());

    public static void main(String[] args) {
        Client client = Client.create();

        // definiuje adres URL testowanego zasobu
        WebResource webResource = client.resource(
                    "http://localhost:8080/customer/rest/Customer");

        // test metody POST
        Customer customer = new Customer();
        Address address = new Address();
        customer.setAddress(address);

        customer.setId(1);
        customer.setFirstname("Duke");
        customer.setLastname("OfJava");
        address.setNumber(1);
        address.setStreet("Duke's Drive");
        address.setCity("JavaTown");
        address.setZip("1234");
        address.setState("JA");
        address.setCountry("USA");
        customer.setEmail("duke@java.net");
        customer.setPhone("12341234");

        ClientResponse response = webResource.type("application/xml")
                                             .post(
                    ClientResponse.class,
                    customer);

        logger.log(
            Level.INFO,
            "status POST: {0}",
            response.getStatus());

        if (response.getStatus() == 201) {
            logger.info("POST zakończony sukcesem");
        } else {
            logger.info("POST zakończony porażką");
        }

        // test metody GET z negocjacją typu treści
        response = webResource.path("1")
                              .accept(MediaType.APPLICATION_XML)
                              .get(ClientResponse.class);

        Customer entity = response.getEntity(Customer.class);

        logger.log(
            Level.INFO,
            "status GET: {0}",
            response.getStatus());

        if (response.getStatus() == 200) {
            logger.log(
                    Level.INFO,
                    "GET zakończony sukcesem, miasto to {0}",
                    entity.getAddress().getCity());
        } else {
            logger.info("GET zakończony porażką");
        }

        // test metody DELETE
        response = webResource.path("1")
                              .delete(ClientResponse.class);

        logger.log(
            Level.INFO,
            "status DELETE: {0}",
            response.getStatus());

        if (response.getStatus() == 204) {
            logger.info("DELETE zakończony sukcesem (brak treści)");
        } else {
            logger.info("DELETE zakończony porażką");
        }

        response = webResource.path("1")
                              .accept(MediaType.APPLICATION_XML)
                              .get(ClientResponse.class);
        logger.log(
            Level.INFO,
            "status GET: {0}",
            response.getStatus());

        if (response.getStatus() == 204) {
            logger.info("Po DELETE, żądanie GET nie zwróciło żadnej treści.");
        } else {
            logger.info("Błąd, po DELETE, żądanie GET zwróciło treść.");
        }
    }
}
