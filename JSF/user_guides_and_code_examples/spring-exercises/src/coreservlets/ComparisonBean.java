package coreservlets;

public abstract class ComparisonBean {
  private Customer richestCustomer;
  private Double testAmount;
  private String message;

  public Double getTestAmount() {
    return (testAmount);
  }

  public void setTestAmount(Double testAmount) {
    this.testAmount = testAmount;
  }

  public Customer getRichestCustomer() {
    return(richestCustomer);
  }

  public void setRichestCustomer(Customer richestCustomer) {
    this.richestCustomer = richestCustomer;
  }
  
  public String getMessage() {
    return(message);
  }
  
  public String doComparison() {
    if (testAmount > richestCustomer.getBalance()) {
      return("richer");
    } else {
      return("poorer");
    }
  }
}
