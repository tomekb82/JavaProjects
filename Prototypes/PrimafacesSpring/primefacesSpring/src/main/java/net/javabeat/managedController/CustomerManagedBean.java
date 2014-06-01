package net.javabeat.managedController;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import net.javabeat.spring.model.Customer;
import net.javabeat.spring.service.CustomerService;
import org.springframework.dao.DataAccessException;


/**
 *
 * Customer Managed Bean
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 */
@ManagedBean(name="customerMB")
@RequestScoped
public class CustomerManagedBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
 
    //Spring Customer Service is injected...
    @ManagedProperty(value="#{CustomerService}")
    CustomerService customerService;
 
    List<Customer> customerList;
 
    private int id;
    private String name;
    private String surname;
 
    /**
     * Add Customer
     *
     * @return String - Response Message
     */
    public String addCustomer() {
        try {
            Customer customer = new Customer();
            customer.setId(getId());
            customer.setName(getName());
            customer.setSurname(getSurname());
            getCustomerService().addCustomer(customer);
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }   
 
        return ERROR;
    }
 
    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.setId(0);
        this.setName("");
        this.setSurname("");
    }
 
    /**
     * Get Customer List
     *
     * @return List - Customer List
     */
    public List<Customer> getCustomerList() {
        customerList = new ArrayList<Customer>();
        customerList.addAll(getCustomerService().getCustomers());
        return customerList;
    }
 
    /**
     * Get Customer Service
     *
     * @return ICustomerService - Customer Service
     */
    public CustomerService getCustomerService() {
        return customerService;
    }

    /**
     * Set Customer Service
     *
     * @param customerService ICustomerService - Customer Service
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
 
    /**
     * Set Customer List
     *
     * @param customerList List - Customer List
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
 
    /**
     * Get Customer Id
     *
     * @return int - Customer Id
     */
    public int getId() {
        return id;
    }
 
    /**
     * Set Customer Id
     *
     * @param id int - Customer Id
     */
    public void setId(int id) {
        this.id = id;
    }
 
    /**
     * Get Customer Name
     *
     * @return String - Customer Name
     */
    public String getName() {
        return name;
    }
 
    /**
     * Set Customer Name
     *
     * @param name String - Customer Name
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /**
     * Get Customer Surname
     *
     * @return String - Customer Surname
     */
    public String getSurname() {
        return surname;
    }
 
    /**
     * Set Customer Surname
     *
     * @param surname String - Customer Surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
 
}