package pl.qualent.allianz.documents;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.jBPMService;


@Named
@Scope("request")
public class DocumentsBean {

	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private UserSession userSession;
	
	private UploadedFile file;  
	
	public void handleFileUpload(FileUploadEvent event) {  
//		String user = userSession.getUsername();
//		long taskId = jBPMService.getTasksForUser(user).get(0).getId();
//		jBPMService.completeTask(taskId, user);
    } 
	
	public void submit(){	
		String user = userSession.getUsername();
		long taskId = jBPMService.getFirstTaskForUser(user, userSession.getProcessInstanceId()).getId();
		jBPMService.completeTask(taskId, user);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
}