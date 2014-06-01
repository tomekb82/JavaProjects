package pl.qualent.allianz;

import javax.inject.Named;

import org.drools.runtime.process.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;


@Named
@Scope("session")
public class UserBean {
	
	@Autowired
	private jBPMService jBPMService;

	
//	@PersistenceContext(unitName="org.jbpm.persistence.jpa")
//	private EntityManager em;

	@Transactional
	public String printMsgFromSpring() {
		   

        //String changeSetUrl = "http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/testp/LATEST/ChangeSet.xml";

		
		String user = "krisv";
		
		ProcessInstance processInstance = jBPMService.startProcess("com.sample.bpmn");
		long taskId = jBPMService.getTasksForUser(user).get(0).getId();

		jBPMService.startTask(taskId, user);
		System.out.println("Human task says: " + jBPMService.getTaskContent(taskId));

		String result = "Id: "+processInstance.getId()+" Process Id: "
		+processInstance.getProcessId()+" Process Name: "+processInstance.getProcessName();
		
        //jBPMService.enableGuvnorRepository(changeSetUrl, "admin", "admin");
        //jBPMService.startProcess("testp.TestProcess");
		
		return result;
	}

	
}