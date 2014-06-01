package pl.qualent.allianz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.NodeInstance;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.task.Status;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.local.LocalTaskService;

public interface jBPMService {

	public List<TaskSummary> getTasksForUser(String user);
	
	public List<TaskSummary> getTasksForOwner(String user);
	
	public TaskSummary getFirstTaskForUser(String user, Long processInstanceId);
	
	public ProcessInstance startProcess(String process);
	
	public ProcessInstance startProcess(String process, Map<String,Object> parameters);
	
	public ProcessInstance getProcessInstance(long processInstanceId);
	
	public void startTask(long taskId, String user);
	
	public void startFirstTask(String user);
	
    public Object getTaskContent(long taskId);    	
	
	public void completeTask(long taskId, String user);

	public void completeTaskWithResults(long taskId, String user, Object results);
	
	public Object getVariable(String name, long processInstanceId); 
	
	public Map<String,Object> getVariables(long processInstanceId); 
	
	public Collection<NodeInstance> getActiveNodeInstances(long processInstanceId); 
	
	public void enableGuvnorRepository(String changeSetUrl, String username, String password);
	
	public StatefulKnowledgeSession getKsession();
	
	public LocalTaskService getLocalTaskService();
	
	public ArrayList<String> getProcessGraphicalStatus(String processInstanceId) throws Exception ;
	
	public String getITextName(long taskId);
	
	public TaskSummary getFirstReadyTaskForUser(String user);
	
}
