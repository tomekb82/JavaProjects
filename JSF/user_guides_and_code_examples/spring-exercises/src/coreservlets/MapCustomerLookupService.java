package coreservlets;

import java.util.*;

/** A simple implementation of CustomerLookupService, for
 *  testing purposes. Uses a Map of Customers.
 *  
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">
 *  the coreservlets.com Java EE tutorials</a>.
 */

public class MapCustomerLookupService 
       implements CustomerLookupService {
  private Map<String,Customer> sampleCustomers;
  
  public Map<String,Customer> getSampleCustomers() {
    return sampleCustomers;
  }

  public void setSampleCustomers(Map<String,Customer> sampleCustomers) {
    this.sampleCustomers = sampleCustomers;
  }
  
  /** Given an id, returns either the corresponding Customer
   *  object or null. Real ids are in lower case, but this
   *  accepts input in any case.
   */
  
  public Customer findCustomer(String id) {
    if (id == null) {
      id = "unknown";
    }
    return(sampleCustomers.get(id.toLowerCase()));
  }

  /** Returns the customer with the highest balance. */
  
  public Customer getRichestCustomer() {
    double maxBalance = -Double.MAX_VALUE;
    Customer richestCustomer = null; 
    for(Customer c: sampleCustomers.values()) {
      if (c.getBalance() > maxBalance) {
        maxBalance = c.getBalance();
        richestCustomer = c;
      }
    }
    return(richestCustomer);
  }
}
