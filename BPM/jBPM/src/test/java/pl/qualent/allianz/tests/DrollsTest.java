package pl.qualent.allianz.tests;


import java.util.HashMap;
import java.util.List;
import java.util.Map;







import org.drools.KnowledgeBase;
import org.drools.builder.ResourceType;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.task.TaskService;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.qualent.allianz.Person;
import pl.qualent.allianz.Request;
import pl.qualent.allianz.ServiceRepository;
import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.UserSessionImpl;
import pl.qualent.allianz.jBPMNavigationHandler;

import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;


/**
 * This is a sample file to test a process.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( {ServiceRepository.class, jBPMNavigationHandler.class} )
public class DrollsTest extends JbpmJUnitTestCase {

	boolean debug = true;
	boolean info = true;
	boolean mock = true;
	
	public DrollsTest() {
		super(true);
		setPersistence(true);
	}
    
	/* Test all diagram for user: 'krisv' */
	@Test
	public void testPoorCustomer() {
		
		/* Create knowledge base and ksession */
		Map<String, ResourceType> kbtypes = new HashMap<String, ResourceType>();
		kbtypes.put("financerules.drl", ResourceType.DRL);
		kbtypes.put("AllianzDrolls.bpmn2", ResourceType.BPMN2);
		
		KnowledgeBase kbase = null;
		try {
			kbase = createKnowledgeBase(kbtypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		StatefulKnowledgeSession ksession = createKnowledgeSession(kbase); 
		
		// setup of a Person and Request.
		Person underagedEval = getUnderagedCustomer();
		Request richEval = getRichCustomer();
		ksession.insert(underagedEval);
		
		//Map to be passed to the startProcess.
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("person", underagedEval);
		params.put("request", richEval);
		
		/* Start process */
		if (debug) System.out.println("testAllDiagram(): Starting process...");	
		ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", params);
		ksession.insert(processInstance);
		ksession.fireAllRules();
		
		if (debug) System.out.println("testAllDiagram(): Executing [Start]");
		assertNodeTriggered(processInstance.getId(), "Start");
			
		if (debug) System.out.println("testAllDiagram(): Executing [Initialize]");
		assertNodeTriggered(processInstance.getId(), "Initialize");
		
		if(debug) System.out.println("testAllDiagram(): Executing [Rule]");
		assertNodeTriggered(processInstance.getId(), "Rule");
		
		if(debug) System.out.println("testAllDiagram(): Executing [check_Customer]");
		assertNodeTriggered(processInstance.getId(), "check_Customer");
		
		//if(debug) System.out.println("testAllDiagram(): Executing [Rich Customer]");
		//assertNodeTriggered(processInstance.getId(), "Rich Customer");
		
		//if(debug) System.out.println("testAllDiagram(): Executing [Poor Customer]");
		//assertNodeTriggered(processInstance.getId(), "Poor Customer");
		
		//if(debug) System.out.println("testAllDiagram(): Executing [End Poor]");
		//assertNodeTriggered(processInstance.getId(), "End Poor");
		
		// check whether the process instance has completed successfully
		assertProcessInstanceCompleted(processInstance.getId(), ksession);
	
		if (debug) System.out.println("#############################################");
		if (info) System.out.println("STATUS: testAllDiagram() finished successful.");
	 
	}
	
	
	private Request getPoorCustomer() {
		Request request = new Request("1");
		request.setPersonId("erics");
		request.setAmount(50);
		return request;
	}
	
	private Request getRichCustomer() {
		Request request = new Request("1");
		request.setPersonId("erics");
		request.setAmount(5000);
		return request;
	}
	
	private Person getUnderagedCustomer() {
		Person person = new Person("erics", "Eric D. Schabell");
		person.setAge(5);
		return person;
	}
	
	private Person getAdultCustomer() {
		Person person = new Person("erics", "Eric D. Schabell");
		person.setAge(50);
		return person;
	}

	}
