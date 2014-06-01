package pl.qualent.allianz.login;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.jbpm.task.query.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.jBPMNavigationHandler;
import pl.qualent.allianz.jBPMService;


@Named
@Scope("request")
public class LoginUserBean {

	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private UserSession sessionBean;
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void submit() throws Exception{
		InputStreamReader input = new InputStreamReader(getClass().getResourceAsStream("/roles.properties"));
		Properties prop = new Properties();
		prop.load(input);
		
		if(prop.getProperty(username)==null){
			FacesContext fc = FacesContext.getCurrentInstance();				
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie znaleziono użytkownika", "Nie znaleziono użytkownika"));
			return;
		}
		
		sessionBean.setUsername(username);
		sessionBean.setProcessInstanceId(null);
		List<TaskSummary> tasks = jBPMService.getTasksForOwner(username);
		if(tasks.size()==0){
			Long processId = jBPMService.startProcess("allianz.Allianz").getId();
			sessionBean.setProcessInstanceId(processId);
		}else{
			jBPMNavigationHandler.navigate("TASKS");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}