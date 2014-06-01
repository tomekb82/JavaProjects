package pl.qualent.allianz;

import java.util.List;

import org.drools.event.process.ProcessCompletedEvent;
import org.drools.event.process.ProcessEventListener;
import org.drools.event.process.ProcessNodeLeftEvent;
import org.drools.event.process.ProcessNodeTriggeredEvent;
import org.drools.event.process.ProcessStartedEvent;
import org.drools.event.process.ProcessVariableChangedEvent;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.workflow.instance.impl.WorkflowProcessInstanceImpl;
import org.jbpm.workflow.instance.node.HumanTaskNodeInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessListener implements ProcessEventListener{

	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private UserSession sessionBean;
	
	@Override
	public void afterNodeLeft(ProcessNodeLeftEvent arg0) {
		
	}

	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		
		if(event.getNodeInstance() instanceof HumanTaskNodeInstance){
			String user = sessionBean.getUsername();
			Long processInstanceId = sessionBean.getProcessInstanceId();
			Long taskId = null;
			
			if(processInstanceId!=null && processInstanceId>0){
				List<TaskSummary> tasks = jBPMService.getTasksForUser(user);
				for(TaskSummary task : tasks){
					if(task.getProcessInstanceId()==processInstanceId){
						taskId = task.getId();
						break;
					}
				}
			}else{
				TaskSummary taskSummary = jBPMService.getFirstReadyTaskForUser(user);
				taskId = taskSummary.getId();
			}
			
			jBPMService.startTask(taskId, user);
		}
	}
	
	@Override
	public void afterProcessCompleted(ProcessCompletedEvent arg0) {
		
		jBPMNavigationHandler.navigate("PROCESSEND");
	}

	@Override
	public void afterProcessStarted(ProcessStartedEvent arg0) {
		
	}

	@Override
	public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
		
	}

	@Override
	public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {
		
	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent arg0) {
		
	}

	@Override
	public void beforeProcessCompleted(ProcessCompletedEvent arg0) {
		String response = (String) ((WorkflowProcessInstanceImpl)arg0.getProcessInstance()).getVariable("responseTxt");
		sessionBean.setResponse(response);
	}

	@Override
	public void beforeProcessStarted(ProcessStartedEvent arg0) {
		
	}

	@Override
	public void beforeVariableChanged(ProcessVariableChangedEvent arg0) {
		
	}

	public jBPMService getjBPMService() {
		return jBPMService;
	}

	public void setjBPMService(jBPMService jBPMService) {
		this.jBPMService = jBPMService;
	}

}
