package pl.qualent.allianz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRepository {

	private static TestService testService;
	
	private static jBPMService jBPMService;
	
	private static UserSession userSession;
	
	public static TestService getTestServiceInstance(){
		return testService;
	}
	
	public static jBPMService getjBPMServiceInstance(){
		return jBPMService;
	}
	
	public static UserSession getUserSessionInstance(){
		return userSession;
	}
	
    @Autowired
    public void setTestService(TestService testService){
    	ServiceRepository.testService = testService;
    }

    @Autowired
	public void setjBPMService(jBPMService jBPMService) {
		ServiceRepository.jBPMService = jBPMService;
	}

    @Autowired
	public void setUserSession(UserSession userSession) {
		ServiceRepository.userSession = userSession;
	}

}
