package pl.qualent.allianz;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

public class jBPMNavigationHandler {

	  public static void navigate(String navigateTo){
		FacesContext fctx = FacesContext.getCurrentInstance();
		Application application = fctx.getApplication();
		NavigationHandler navHandler = application.getNavigationHandler();
		navHandler.handleNavigation(fctx,null, navigateTo);

	  }
	
}
