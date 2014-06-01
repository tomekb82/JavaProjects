package coreservlets;

/** Interface for services that let you look up customers
 *  based on their customer ID, and for finding the
 *  customer with the highest balance.
 *  
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">
 *  the coreservlets.com Java EE tutorials</a>.
 */

public interface CustomerLookupService {
  
  public Customer findCustomer(String id);
  
  public Customer getRichestCustomer();
}
