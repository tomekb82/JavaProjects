/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package customer.rest;

import customer.data.Address;
import customer.data.Customer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


/**
 * Usługa sieciowa typu REST z metodami CRUD
 *
 * @author markito
 */
@Path("/Customer")
public class CustomerService {
    public static final String DATA_STORE = "CustomerDATA.txt";
    public static final Logger logger = Logger.getLogger(
                CustomerService.class.getCanonicalName());

    /**
     * Pobierz XML klienta
     *
     * @param customerId
     * @return Customer
     */
    @GET
    @Path("{id}")
    @Produces({
        "application/xml",
        "application/json"
    })
    public Customer getCustomer(@PathParam("id")
    String customerId) {
        Customer customer = null;

        try {
            customer = findById(customerId);
        } catch (Exception ex) {
            logger.log(
                    Level.SEVERE,
                    "Error calling searchCustomer() for customerId {0}. {1}",
                    new Object[] { customerId, ex.getMessage() });
        }

        return customer;
    }

    /**
     * Metoda createCustomer bazująca na 
     * <code>CustomerType</code>
     *
     * @param customer
     * @return adres URI dodanego klienta
     * @see Customer.java
     */
    @POST
    @Consumes({
        "application/xml",
        "application/json"
    })
    public Response createCustomer(Customer customer) {
        try {
            long customerId = persist(customer);

            return Response.created(URI.create("/" + customerId))
                           .build();
        } catch (Exception e) {
            throw new WebApplicationException(
                    e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Aktualizacja zasobu
     *
     * @param customer
     * @return adres URI aktualizowanego klienta
     * @see Customer.java
     */
    @PUT
    @Path("{id}")
    @Consumes({
        "application/xml",
        "application/json"
    })
    public Response updateCustomer(
        @PathParam("id")
    String customerId,
        Customer customer) {
        try {
            Customer oldCustomer = findById(customerId);

            if (oldCustomer == null) {
                // zwróć informację o nieodnalezieniu elementu w formacie http
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                persist(customer);

                return Response.ok()
                               .status(303)
                               .build(); //zwraca kod seeOther
            }
        } catch (Exception e) {
            throw new WebApplicationException(
                    e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Usunięcie zasobu
     *
     * @param customerId
     */
    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id")
    String customerId) {
        try {
            if (!remove(customerId)) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (IOException ex) {
            logger.log(
                    Level.SEVERE,
                    "Błąd wywołania deleteCustomer() dla customerId {0}. {1}",
                    new Object[] { customerId, ex.getMessage() });
        }
    }

    /**
     * Prosta meetoda trwałości danych symulująca zachowanie JPA przy użyciu
     * <code>java.util.Properties</code>
     *
     * @param customer
     * @return customerId long
     */
    private long persist(Customer customer) throws IOException {
        File dataFile = new File(DATA_STORE);

        // pierwsze uruchomienie
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        // nadpisze ten sam identyfikator
        long customerId = customer.getId();
        Address address = customer.getAddress();

        Properties properties = new Properties();
        properties.load(new FileInputStream(dataFile));

        properties.setProperty(
                String.valueOf(customerId),
                customer.getFirstname() + "," + customer.getLastname() + ","
                + address.getNumber() + "," + address.getStreet() + ","
                + address.getCity() + "," + address.getState() + ","
                + address.getZip() + "," + address.getCountry() + ","
                + customer.getEmail() + "," + customer.getPhone());

        properties.store(
            new FileOutputStream(DATA_STORE),
            null);

        return customerId;
    }

    /**
     * Prosta metoda zapytania symulująca zachowanie JPA przy użyciu
     * <code>java.util.Properties</code>
     *
     * @param customerId
     * @return Customer
     * @throws IOException
     */
    private Customer findById(String customerId) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DATA_STORE));

        String rawData = properties.getProperty(customerId);

        if (rawData != null) {
            final String[] field = rawData.split(",");

            Address address = new Address();
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(customerId));
            customer.setAddress(address);

            customer.setFirstname(field[0]);
            customer.setLastname(field[1]);
            address.setNumber(Integer.parseInt(field[2]));
            address.setStreet(field[3]);
            address.setCity(field[4]);
            address.setState(field[5]);
            address.setZip(field[6]);
            address.setCountry(field[7]);
            customer.setEmail(field[8]);
            customer.setPhone(field[9]);

            return customer;
        }

        return null;
    }

    /**
     * Prosta metoda usunięcia symulująca zachowanie JPA przy użyciu
     * <code>java.util.Properties</code>
     *
     * @param customerId
     * @return boolean
     * @throws IOException
     */
    private boolean remove(String customerId) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DATA_STORE));

        String rawData = properties.getProperty(customerId);

        if (rawData == null) {
            return false;
        } else {
            properties.remove(customerId);
            properties.store(
                new FileOutputStream(DATA_STORE),
                null);

            return true;
        }
    }
}
