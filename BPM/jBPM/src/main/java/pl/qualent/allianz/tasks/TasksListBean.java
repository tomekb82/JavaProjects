package pl.qualent.allianz.tasks;

import java.util.List;

import javax.inject.Named;

import org.jbpm.task.query.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.jBPMNavigationHandler;
import pl.qualent.allianz.jBPMService;


@Named
@Scope("request")
public class TasksListBean {

	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private UserSession sessionBean;

	public List<TaskSummary> getTasks(){
		return jBPMService.getTasksForOwner(sessionBean.getUsername());
	}
	
	public void submit(TaskSummary taskSummary){
//		jBPMService.getLocalTaskService().release(task.getId(), sessionBean.getUsername());
//		jBPMService.getLocalTaskService().start(task.getId(), sessionBean.getUsername());
		String iText = jBPMService.getITextName(taskSummary.getId());
		String redirect = null;
		if(iText.equals("Polisy")){
			redirect = "SELECTPOLICY";
		}else if(iText.equals("Szkoda")){
			redirect = "CLAIMDETAILS";
		}else if(iText.equals("Uszkodzenie")){
			redirect = "DAMAGEDETAILS";
		}else if(iText.equals("Dokumenty")){
			redirect = "DOCUMENTS";
		}else if(iText.equals("Porozumienie")){
			redirect = "SELECTSETTLEMENT";
		}
		sessionBean.setProcessInstanceId(taskSummary.getProcessInstanceId());
		jBPMNavigationHandler.navigate(redirect);
	}
	
	public void startProcess(){
		Long processId = jBPMService.startProcess("allianz.Allianz").getId();
		sessionBean.setProcessInstanceId(processId);
	}
	
	public String getDiagramUrl(TaskSummary taskSummary){
		return "/drawDiagram.xhtml?faces-redirect=true&includeViewParams=true&processInstanceId="+String.valueOf(taskSummary.getProcessInstanceId());
	}
	
}