package pl.qualent.allianz.process;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.jBPMService;


@Named
@Scope("request")
public class ProcessEndBean {

	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private UserSession sessionBean;
	
	
	public String getResponse(){
		return sessionBean.getResponse();
	}
	
}