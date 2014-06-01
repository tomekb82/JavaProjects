package coreservlets;

import javax.faces.bean.*;

@ManagedBean
public class ComparisonBean1 extends ComparisonBean {
  public ComparisonBean1() {
    Customer larry = 
      new Customer("a0001", "Larry", "Ellison", 123456789.01);
    setRichestCustomer(larry);
  }
}
