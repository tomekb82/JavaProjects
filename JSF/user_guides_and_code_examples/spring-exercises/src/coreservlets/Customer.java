package coreservlets;

public class Customer {
  private String customerID, firstName, lastName;
  private double balance;
  
  public Customer(String customerID, String firstName, String lastName, double balance) {
    this.customerID = customerID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.balance = balance;
  }
  
  public Customer() {}
  
  public String getCustomerID() {
    return (customerID);
  }
  
  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }
  
  public String getFirstName() {
    return (firstName);
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return (lastName);
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public double getBalance() {
    return (balance);
  }
  
  public void setBalance(double balance) {
    this.balance = balance;
  }
  
  public String getFormattedBalance() {
    return(String.format("$%,.2f", getBalance()));
  }
  
  public String getBalanceNoSign() {
    String balanceString = 
      String.format("%,.2f", Math.abs(balance));
    return(balanceString);
  }
}
