package pl.qualent.allianz;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentConfiguration;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.definition.process.Node;
import org.drools.impl.EnvironmentFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.NodeInstance;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.process.audit.JPAProcessInstanceDbLog;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;
import org.jbpm.process.audit.NodeInstanceLog;
import org.jbpm.process.workitem.email.EmailWorkItemHandler;
import org.jbpm.process.workitem.wsht.LocalHTWorkItemHandler;
import org.jbpm.task.Content;
import org.jbpm.task.Task;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.local.LocalTaskService;
import org.jbpm.task.utils.ContentMarshallerHelper;
import org.jbpm.task.utils.OnErrorAction;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.instance.impl.WorkflowProcessInstanceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import pl.qualent.allianz.services.TaskServiceImpl;



@Transactional
public class jBPMServiceImpl implements jBPMService{
	
	private StatefulKnowledgeSession ksession;
	
	private LocalTaskService localTaskService;
	
	private EntityManagerFactory emf;
	
	private AbstractPlatformTransactionManager aptm;
	
	private Environment env;
	
	@Autowired
	private ProcessListener processListener;
	
	@Autowired
	private TaskServiceImpl taskService;

    @PostConstruct
    public void initialize() {
    	
    	ksession.addEventListener(processListener);
    	new JPAWorkingMemoryDbLogger(ksession);
	    LocalHTWorkItemHandler humanTaskHandler = new LocalHTWorkItemHandler(localTaskService, 
	    		ksession, OnErrorAction.LOG);		
		humanTaskHandler.setLocal(true);
		humanTaskHandler.connect();
//		EmailWorkItemHandler emailHandler1 = new EmailWorkItemHandler();
//		emailHandler1.setConnection("smtp.gmail.com","587", "testjbpm295@gmail.com", "allianz123");
//		emailHandler1.getConnection().setStartTls(true);
//		ksession.getWorkItemManager().registerWorkItemHandler("Email", emailHandler1);
		ksession.getWorkItemManager().registerWorkItemHandler("Human Task", humanTaskHandler);
		
		
	 	env = EnvironmentFactory.newEnvironment();  
		
		env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, emf);
		      
//        TransactionManager transactionManager = new DroolsSpringTransactionManager( aptm );
//        env.set(EnvironmentName.TRANSACTION_MANAGER, transactionManager);
//
//        PersistenceContextManager persistenceContextManager = new DroolsSpringJpaManager(env);
//        env.set(EnvironmentName.PERSISTENCE_CONTEXT_MANAGER, persistenceContextManager);
//		
		JPAProcessInstanceDbLog.setEnvironment(env);
    }
	
    public Object getTaskContent(long taskId){    	
    	Task task = localTaskService.getTask(taskId);
    	Content content = localTaskService.getContent(task.getTaskData().getDocumentContentId());
    	return (ContentMarshallerHelper.unmarshall(content.getContent(), null));   	 
    }
    
	@Override
	public List<TaskSummary> getTasksForUser(String user) {
		
		return localTaskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
	}
	
	public List<TaskSummary> getTasksForOwner(String user) {
		List<org.jbpm.task.Status> status = new ArrayList<org.jbpm.task.Status>();
		status.add(org.jbpm.task.Status.InProgress);
		return localTaskService.getTasksOwned(user, status, "en-UK");
	}

	@Override
	public TaskSummary getFirstTaskForUser(String user, Long processInstanceId) {
		List<TaskSummary> userTasks = getTasksForOwner(user);
		TaskSummary task = null;
		if(processInstanceId!=null && processInstanceId>0){
			for(TaskSummary userTask : userTasks){
				if(userTask.getProcessInstanceId()==processInstanceId){
					task = userTask;
					break;
				}
			}
		}
		if(task==null){
			Collections.sort(userTasks, new TaskSummaryComparator());
			task = userTasks.get(0);
		}
		return task;
	}
	

	@Override
	public ProcessInstance startProcess(String process) {
		
		return ksession.startProcess(process);
	}

	@Override
	public ProcessInstance startProcess(String process,
			Map<String, Object> parameters) {
		
		return ksession.startProcess(process,parameters);
	}

	@Override
	public ProcessInstance getProcessInstance(long processInstanceId) {
		return ksession.getProcessInstance(processInstanceId);
	}

	@Override
	public void startTask(long taskId, String user) {
		localTaskService.start(taskId, user);
		
	}

	@Override
	public void startFirstTask(String user) {
		localTaskService.start(getFirstTaskForUser(user, null).getId(), user);
		
	}

	@Override
	public void completeTask(long taskId, String user) {
		localTaskService.complete(taskId, user, null);
		
	}
	
	@Override
	public void completeTaskWithResults(long taskId, String user, Object results) {
//		org.jbpm.task.Task taks = localTaskService.getTask(taskId);
//		taks.getTaskData().getWorkItemId();
		localTaskService.completeWithResults(taskId, user, results);
		
	}

	public StatefulKnowledgeSession getKsession() {
		return ksession;
	}

	public void setKsession(StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
	}

	public LocalTaskService getLocalTaskService() {
		return localTaskService;
	}

	public void setLocalTaskService(LocalTaskService localTaskService) {
		this.localTaskService = localTaskService;
	}

	@Override
	public Object getVariable(String name, long processInstanceId) {
		return ((WorkflowProcessInstanceImpl)getProcessInstance(processInstanceId)).getVariable(name);
	}

	@Override
	public Map<String, Object> getVariables(long processInstanceId) {
		return ((WorkflowProcessInstanceImpl)getProcessInstance(processInstanceId)).getVariables();
	}

	@Override
	public Collection<NodeInstance> getActiveNodeInstances(
			long processInstanceId) {
		
		return ((WorkflowProcessInstanceImpl)getProcessInstance(processInstanceId)).getNodeInstances();
	}

	@Override
	public void enableGuvnorRepository(String changeSetUrl, final String username,
			final String password) {
		
		KnowledgeAgentConfiguration conf = KnowledgeAgentFactory.newKnowledgeAgentConfiguration();
        Authenticator.setDefault(new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                		username,
                		password.toCharArray());
            }
        });

        KnowledgeAgent knowledgeAgent = KnowledgeAgentFactory.newKnowledgeAgent("myAgent",    conf);
        knowledgeAgent.applyChangeSet(ResourceFactory.newUrlResource(changeSetUrl));
        
        KnowledgeBase kbase = knowledgeAgent.getKnowledgeBase();
        ksession = kbase.newStatefulKnowledgeSession();
		
	}
	
	public ArrayList<String> getProcessGraphicalStatus(String processInstanceId) throws Exception  
	 {  
		
	    String processDefId = JPAProcessInstanceDbLog.findProcessInstance(Long.parseLong(processInstanceId)).getProcessId();  
	    KnowledgeBase kbase = ksession.getKnowledgeBase();
	    org.drools.definition.process.Process process = kbase.getProcess(processDefId);  
	    
	   
	  ArrayList<String> coordinates = new ArrayList<String>();  
	  try  
	  {  
	   List<NodeInstanceLog> nodeInstanceLogList;  
	   nodeInstanceLogList = JPAProcessInstanceDbLog.findNodeInstances(Long.parseLong(processInstanceId));  
	   for (Node node : (Node[])((WorkflowProcessImpl) process).getNodes())  
	   {  
//	    nodeInstanceLogList = JPAProcessInstanceDbLog.findNodeInstances(Long.parseLong(processInstanceId), new Long(node.getId()).toString());  
	    if(nodeInstanceLogList.get(nodeInstanceLogList.size()-1).getNodeId().equals(new Long(node.getId()).toString()))  
	    {  
	    	 Integer x = (Integer) node.getMetaData().get("x");
	    	 Integer y = (Integer) node.getMetaData().get("y");
	    	 Integer height = (Integer) node.getMetaData().get("height");
	    	 Integer width = (Integer) node.getMetaData().get("width");
		     coordinates.add(x+"");  
		     coordinates.add(y+height/2+"");  
		     coordinates.add(height+"");  
		     coordinates.add(width+"");  
	    }  
	   }  
	  }  
	  catch(Exception e)  
	  {  
	   e.printStackTrace();  
	  }
	  return coordinates;  
	 }

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public AbstractPlatformTransactionManager getAptm() {
		return aptm;
	}

	public void setAptm(AbstractPlatformTransactionManager aptm) {
		this.aptm = aptm;
	}

	@Override
	public String getITextName(long taskId) {
		
//		Task task = localTaskService.getTask(taskId);
//		String iText = task.getNames().get(0).getText();
		return taskService.getITextName(taskId);
	}

	@Override
	public TaskSummary getFirstReadyTaskForUser(String user) {
		List<TaskSummary> userTasks = localTaskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
		Collections.sort(userTasks, new TaskSummaryComparator());
		return userTasks.get(0);
	}
}
