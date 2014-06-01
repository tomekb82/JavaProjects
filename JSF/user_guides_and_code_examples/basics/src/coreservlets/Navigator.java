package coreservlets;

import javax.faces.bean.*;

@ManagedBean
@ApplicationScoped  // See Managed Beans II lecture for explanation of this minor optimization. Can be omitted.  
public class Navigator {
  private String[] resultPages =
    { "page1", "page2", "page3" };
  
  public String choosePage() {
    return(RandomUtils.randomElement(resultPages));
  }
}
