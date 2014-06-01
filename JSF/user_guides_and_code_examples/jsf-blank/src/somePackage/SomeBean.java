package somePackage;

import javax.faces.bean.*;

@ManagedBean
public class SomeBean {
  private String someProperty;

  public String getSomeProperty() {
    return(someProperty);
  }

  public void setSomeProperty(String someProperty) {
    this.someProperty = someProperty;
  }
  
  public String someActionControllerMethod() {
    return("page-b");  // Means to go to page-b.xhtml (since condition is not mapped in faces-config.xml)
  }
  
  public String someOtherActionControllerMethod() {
    return("page-a");  // Means to go to page-a.xhtml (since condition is not mapped in faces-config.xml)
  }
}
