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
public class ProcessTest extends JbpmJUnitTestCase {

	boolean debug = true;
	boolean info = true;
	boolean mock = true;
	
	public ProcessTest() {
		super(true);
		setPersistence(true);
	}
    
	/* Test all diagram for user: 'krisv' */
	@Test
	public void testAllDiagram_Krisv() {
		
		/* Set process variables */
		String user = "krisv";     // INFO: change it to test different path
		String cause = "damage";   // INFO: change it to test different path
		String policyId = "2";     // INFO: change it to test different path
		String settlementId = "1"; // (1: naprawa) INFO: change it to test different path
	
		if (mock){
		    UserSession userSession = new UserSessionImpl();
		    userSession.setUsername(user);
			
		    PowerMockito.mockStatic(ServiceRepository.class);
		    PowerMockito.mockStatic(jBPMNavigationHandler.class);
		
		    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
		}
		
		//Map<String, Object> map = new HashMap<String, Object>();
		Map map = new HashMap();
		//map.put("user",user);
		map.put("policyId", policyId);
		map.put("cause", cause);
		map.put("settlementId", settlementId);
		
		/* Create session and task service */
		if (debug) System.out.println("testAllDiagram(): Creating session and task service...");
		/*
		Map<String, ResourceType> kbtypes = new HashMap<String, ResourceType>();
		kbtypes.put("financerules.drl", ResourceType.DRL);
		kbtypes.put("Allianz.bpmn2", ResourceType.BPMN2);
		
		
			
		KnowledgeBase kbase = null;
		try {
			kbase = createKnowledgeBase(kbtypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		StatefulKnowledgeSession ksession = createKnowledgeSession(kbase); 
		*/		
		StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");
		TaskService taskService = getTaskService(ksession);
		
		
		// setup of a Person and Request.
		//Person underagedEval = getUnderagedCustomer();
		//Request richEval = getRichCustomer();
		//ksession.insert(underagedEval);
		
		//Map to be passed to the startProcess.
		//Map<String, Object> params = new HashMap<String, Object>();
		//params.put("person", underagedEval);
		//params.put("request", richEval);
		
		/* Start process */
		if (debug) System.out.println("testAllDiagram(): Starting process...");	
		ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);
		//ksession.insert(processInstance);
		//ksession.fireAllRules();
		
		if (debug) System.out.println("testAllDiagram(): Executing [Start]");
		assertNodeTriggered(processInstance.getId(), "Start");
			
		if (debug) System.out.println("testAllDiagram(): Executing [Sprawdz ilosc polis]");
		assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");

		if (debug) System.out.println("testAllDiagram(): Executing [Wiecej niz 1 polisa]");
		assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
		
		// Let user execute 'Polisy' 
		if("krisv".equals(user)){		
			if (debug) System.out.println("testAllDiagram(): Executing [Wybierz polise]");
		    assertNodeTriggered(processInstance.getId(), "Wybierz polise"); 
		    List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
		    TaskSummary task = list.get(0);
		    if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task.getName());
		    taskService.start(task.getId(), user);
		    taskService.completeWithResults(task.getId(), user, map);	
		}
		if (debug) System.out.println("testAllDiagram(): Executing [startWeryfikacja]");
		assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
		if (debug) System.out.println("testAllDiagram(): Executing [Sprawdz polise]");
		assertNodeTriggered(processInstance.getId(), "Sprawdz polise");
		if (debug) System.out.println("testAllDiagram(): Executing [Czy polisa wazna]");
		assertNodeTriggered(processInstance.getId(), "Czy polisa wazna");		
		if (debug) System.out.println("testAllDiagram(): Executing [Uzupelnij formularz danymi]");
		assertNodeTriggered(processInstance.getId(), "Uzupelnij formularz danymi");
		
		//Let user execute 'Szkoda' 
		if (debug) System.out.println("testAllDiagram(): Executing [Uzupelnienie szczegolow szkody]");
		assertNodeTriggered(processInstance.getId(), "Uzupelnienie szczegolow szkody"); // TODO: doesn't work???
		List<TaskSummary> list2 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
		TaskSummary task2 = list2.get(0);
		if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task2.getName());
	    taskService.start(task2.getId(), user);
	    taskService.completeWithResults(task2.getId(), user, map); // INFO: paramters required (for result mapping)
	    
	    if (debug) System.out.println("testAllianz(): Executing [Uszkodzenie?]");
	    assertNodeTriggered(processInstance.getId(), "Uszkodzenie?"); 
	   
	    //Let user execute 'damageDetails' 
	    if("damage".equals(cause)){
	    	if (debug) System.out.println("testAllDiagram(): Executing [Uzupelnij szczegoly uszkodzenia]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnij szczegoly uszkodzenia"); 
	 		List<TaskSummary> list3 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
	 		TaskSummary task3 = list3.get(0);
	 		if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task3.getName());
	 	    taskService.start(task3.getId(), user);
	 	    taskService.complete(task3.getId(), user, null);
	    }
	    
	    if (debug) System.out.println("testAllDiagram(): Executing [Wybrany rodzaj szkody]");
	    assertNodeTriggered(processInstance.getId(), "Wybrany rodzaj szkody");
		
	    //Let user execute 'Document Task' 
	    if (debug) System.out.println("testAllDiagram(): Executing [Umiesc dokumenty]");
		assertNodeTriggered(processInstance.getId(), "Umiesc dokumenty"); 
 		List<TaskSummary> list4 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
 		TaskSummary task4 = list4.get(0);
 		if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task4.getName());
 	    taskService.start(task4.getId(), user);
 	    taskService.complete(task4.getId(), user, null);
		
 	    if (debug) System.out.println("testAllDiagram(): Executing [Stworzenie zgloszenia]");
	    assertNodeTriggered(processInstance.getId(), "Stworzenie zgloszenia");
	    if (debug) System.out.println("testAllDiagram(): Executing [Zaproponuj porozumienie]");
	    assertNodeTriggered(processInstance.getId(), "Zaproponuj porozumienie");
	    
	    //Let user execute 'selectAgreement' 
	    if (debug) System.out.println("testAllDiagram(): Executing [Wybierz porozumienie]");
		assertNodeTriggered(processInstance.getId(), "Wybierz porozumienie"); 
 		List<TaskSummary> list5 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
 		TaskSummary task5 = list5.get(0);
 		if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task5.getName());
 	    taskService.start(task5.getId(), user);
 	    taskService.completeWithResults(task5.getId(), user, map); // INFO: paramters required (for result mapping)
 	      
	    if (debug) System.out.println("testAllDiagram(): Rodzaj porozumienia");
	    assertNodeTriggered(processInstance.getId(), "Rodzaj porozumienia");
	    if (debug) System.out.println("testAllDiagram(): Executing [Naprawa]");
	    assertNodeTriggered(processInstance.getId(), "Naprawa"); 
	    if (debug) System.out.println("testAllDiagram(): Wybrane porozumienie");
	    assertNodeTriggered(processInstance.getId(), "Wybrane porozumienie");
	    if (debug) System.out.println("testAllDiagram(): Finish succeeded");
	    assertNodeTriggered(processInstance.getId(), "Koniec");

		// check whether the process instance has completed successfully
		assertProcessInstanceCompleted(processInstance.getId(), ksession);
	
		if (debug) System.out.println("#############################################");
		if (info) System.out.println("STATUS: testAllDiagram() finished successful.");
	 
	}
	
	/* Test all diagram for user: 'john' */
	@Test
	public void testAllDiagram_John() {
		
		/* Set process variables */
		String user = "john";     // INFO: change it to test different path
		String cause = "damage";   // INFO: change it to test different path
		String policyId = "2";     // INFO: change it to test different path
		String settlementId = "1"; // (1: naprawa) INFO: change it to test different path
	
		if (mock){
		    UserSession userSession = new UserSessionImpl();
		    userSession.setUsername(user);
			
		    PowerMockito.mockStatic(ServiceRepository.class);
		    PowerMockito.mockStatic(jBPMNavigationHandler.class);
		
		    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
		}
		
		Map map = new HashMap();
		map.put("user",user);
		map.put("policyId", policyId);
		map.put("cause", cause);
		map.put("settlementId", settlementId);
		
		/* Create session and task service */
		if (debug) System.out.println("testAllDiagram(): Creating session and task service...");
		StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
		TaskService taskService = getTaskService(ksession);
		
		/* Start process */
		if (debug) System.out.println("testAllDiagram(): Starting process...");
		
		ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
		if (debug) System.out.println("testAllDiagram(): Executing [Start]");
		assertNodeTriggered(processInstance.getId(), "Start");
		if (debug) System.out.println("testAllDiagram(): Executing [Sprawdz ilosc polis]");
		assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
		if (debug) System.out.println("testAllDiagram(): Executing [Wiecej niz 1 polisa]");
		assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
		
		// Let user execute 'Polisy' 
		if("krisv".equals(user)){		
			if (debug) System.out.println("testAllDiagram(): Executing [Wybierz polise]");
		    assertNodeTriggered(processInstance.getId(), "Wybierz polise"); 
		    List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
		    TaskSummary task = list.get(0);
		    if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task.getName());
		    taskService.start(task.getId(), user);
		    taskService.completeWithResults(task.getId(), user, map);	
		}
		if (debug) System.out.println("testAllDiagram(): Executing [startWeryfikacja]");
		assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
		if (debug) System.out.println("testAllDiagram(): Executing [Sprawdz polise]");
		assertNodeTriggered(processInstance.getId(), "Sprawdz polise");
		if (debug) System.out.println("testAllDiagram(): Executing [Czy polisa wazna]");
		assertNodeTriggered(processInstance.getId(), "Czy polisa wazna");		
		if (debug) System.out.println("testAllDiagram(): Executing [Uzupelnij formularz danymi]");
		assertNodeTriggered(processInstance.getId(), "Uzupelnij formularz danymi");
		
		//Let user execute 'Szkoda' 
		if (debug) System.out.println("testAllDiagram(): Executing [Uzupelnienie szczegolow szkody]");
		assertNodeTriggered(processInstance.getId(), "Uzupelnienie szczegolow szkody"); // TODO: doesn't work???
		List<TaskSummary> list2 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
		TaskSummary task2 = list2.get(0);
		if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task2.getName());
	    taskService.start(task2.getId(), user);
	    taskService.completeWithResults(task2.getId(), user, map); // INFO: paramters required (for result mapping)
	    
	    if (debug) System.out.println("testAllianz(): Executing [Uszkodzenie?]");
	    assertNodeTriggered(processInstance.getId(), "Uszkodzenie?"); 
	   
	    //Let user execute 'damageDetails' 
	    if("damage".equals(cause)){
	        if (debug) System.out.println("testAllDiagram(): Executing [Uzupelnij szczegoly uszkodzenia]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnij szczegoly uszkodzenia"); 
	 		List<TaskSummary> list3 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
	 		TaskSummary task3 = list3.get(0);
	 		if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task3.getName());
	 	    taskService.start(task3.getId(), user);
	 	    taskService.complete(task3.getId(), user, null);
	    }
	    
	    if (debug) System.out.println("testAllDiagram(): Executing [Wybrany rodzaj szkody]");
	    assertNodeTriggered(processInstance.getId(), "Wybrany rodzaj szkody");
		
	    //Let user execute 'Document Task' 
	    if (debug) System.out.println("testAllDiagram(): Executing [Umiesc dokumenty]");
		assertNodeTriggered(processInstance.getId(), "Umiesc dokumenty"); 
 		List<TaskSummary> list4 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
 		TaskSummary task4 = list4.get(0);
 		if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task4.getName());
 	    taskService.start(task4.getId(), user);
 	    taskService.complete(task4.getId(), user, null);
		
 	    if (debug) System.out.println("testAllDiagram(): Executing [Stworzenie zgloszenia]");
	    assertNodeTriggered(processInstance.getId(), "Stworzenie zgloszenia");
	    if (debug) System.out.println("testAllDiagram(): Executing [Zaproponuj porozumienie]");
	    assertNodeTriggered(processInstance.getId(), "Zaproponuj porozumienie");
	    
	    //Let user execute 'selectAgreement' 
	    if (debug) System.out.println("testAllDiagram(): Executing [Wybierz porozumienie]");
		assertNodeTriggered(processInstance.getId(), "Wybierz porozumienie"); 
 		List<TaskSummary> list5 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
 		TaskSummary task5 = list5.get(0);
 		if (debug) System.out.println("testAllDiagram(): "+ user.toUpperCase() +" executing task: " + task5.getName());
 	    taskService.start(task5.getId(), user);
 	    taskService.completeWithResults(task5.getId(), user, map); // INFO: paramters required (for result mapping)
 	      
	    if (debug) System.out.println("testAllDiagram(): Rodzaj porozumienia");
	    assertNodeTriggered(processInstance.getId(), "Rodzaj porozumienia");
	    if (debug) System.out.println("testAllDiagram(): Executing [Naprawa]");
	    assertNodeTriggered(processInstance.getId(), "Naprawa"); 
	    if (debug) System.out.println("testAllDiagram(): Wybrane porozumienie");
	    assertNodeTriggered(processInstance.getId(), "Wybrane porozumienie");
	    if (debug) System.out.println("testAllDiagram(): Finish succeeded");
	    assertNodeTriggered(processInstance.getId(), "Koniec");

		// check whether the process instance has completed successfully
		assertProcessInstanceCompleted(processInstance.getId(), ksession);
	
		if (debug) System.out.println("#############################################");
		if (info) System.out.println("STATUS: testAllDiagram() finished successful.");
	}
	
	/* testManyPolicies */
	@Test
	public void testManyPolicies() {
		
		/* Set process variables */
		String user = "krisv";     // INFO: change it to test different path
		String cause = "damage";   // INFO: change it to test different path
		String policyId = "2";     // INFO: change it to test different path
		String settlementId = "1"; // (1: naprawa) INFO: change it to test different path

		if (mock){
		    UserSession userSession = new UserSessionImpl();
		    userSession.setUsername(user);
			
		    PowerMockito.mockStatic(ServiceRepository.class);
		    PowerMockito.mockStatic(jBPMNavigationHandler.class);
		
		    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
		}
		
		Map map = new HashMap();
		map.put("user",user);
		map.put("policyId", policyId);
		map.put("cause", cause);
		map.put("settlementId", settlementId);
		
		/* Create session and task service */
		if (debug) System.out.println("testManyPolicies(): Creating session and task service...");
		StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
		TaskService taskService = getTaskService(ksession);
		
		/* Start process */
		if (debug) System.out.println("testManyPolicies(): Starting process...");
		
		ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
		if (debug) System.out.println("testManyPolicies(): Executing [Start]");
		assertNodeTriggered(processInstance.getId(), "Start");
		if (debug) System.out.println("testManyPolicies(): Executing [Sprawdz ilosc polis]");
		assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
		if (debug) System.out.println("testManyPolicies(): Executing [Wiecej niz 1 polisa]");
		assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
		if (debug) System.out.println("testManyPolicies(): Executing [Wybierz polise]");
	    assertNodeTriggered(processInstance.getId(), "Wybierz polise");	
		if (debug) System.out.println("#############################################");
		if (info) System.out.println("STATUS: testManyPolicies() finished successful.");
	}
	
	/* testManyPolicies */
	@Test
	public void testOnePolice() {
		
		/* Set process variables */
		String user = "john";     // INFO: change it to test different path
		String cause = "damage";   // INFO: change it to test different path
		String policyId = "2";     // INFO: change it to test different path
		String settlementId = "1"; // (1: naprawa) INFO: change it to test different path

		if (mock){
		    UserSession userSession = new UserSessionImpl();
		    userSession.setUsername(user);
			
		    PowerMockito.mockStatic(ServiceRepository.class);
		    PowerMockito.mockStatic(jBPMNavigationHandler.class);
		
		    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
		}
		
		Map map = new HashMap();
		map.put("user",user);
		map.put("policyId", policyId);
		map.put("cause", cause);
		map.put("settlementId", settlementId);
				
		/* Create session and task service */
		if (debug) System.out.println("testOnePolice(): Creating session and task service...");
		StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
		TaskService taskService = getTaskService(ksession);
		
		/* Start process */
		if (debug) System.out.println("testOnePolice(): Starting process...");
		
		ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
		if (debug) System.out.println("testOnePolice(): Executing [Start]");
		assertNodeTriggered(processInstance.getId(), "Start");
		if (debug) System.out.println("testOnePolice(): Executing [Sprawdz ilosc polis]");
		assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
		if (debug) System.out.println("testOnePolice(): Executing [Wiecej niz 1 polisa]");
		assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
		if (debug) System.out.println("testOnePolice(): Executing [startWeryfikacja]");
	    assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
		if (debug) System.out.println("#############################################");
		if (info) System.out.println("STATUS: testOnePolice() finished successful.");
	}
	
	@Test
	public void testValidPolicy() {
			
			/* Set process variables */
			String user = "krisv";     // INFO: change it to test different path
			String cause = "damage";   // INFO: change it to test different path
			String policyId = "2";     // INFO: change it to test different path
			String settlementId = "1"; // (1: naprawa) INFO: change it to test different path

			if (mock){
			    UserSession userSession = new UserSessionImpl();
			    userSession.setUsername(user);
				
			    PowerMockito.mockStatic(ServiceRepository.class);
			    PowerMockito.mockStatic(jBPMNavigationHandler.class);
			
			    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
			}
			
			Map map = new HashMap();
			map.put("user",user);
			map.put("policyId", policyId);
			map.put("cause", cause);
			map.put("settlementId", settlementId);
			
			/* Create session and task service */
			if (debug) System.out.println("testValidPolicy(): Creating session and task service...");
			StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
			TaskService taskService = getTaskService(ksession);
			
			/* Start process */
			if (debug) System.out.println("testValidPolicy(): Starting process...");
			
			ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
			if (debug) System.out.println("testValidPolicy(): Executing [Start]");
			assertNodeTriggered(processInstance.getId(), "Start");
			if (debug) System.out.println("testValidPolicy(): Executing [Sprawdz ilosc polis]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
			if (debug) System.out.println("testValidPolicy(): Executing [Wiecej niz 1 polisa]");
			assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
			
			// Let user execute 'Polisy' 
			if("krisv".equals(user)){		
				if (debug) System.out.println("testValidPolicy(): Executing [Wybierz polise]");
			    assertNodeTriggered(processInstance.getId(), "Wybierz polise"); 
			    List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			    TaskSummary task = list.get(0);
			    if (debug) System.out.println("testValidPolicy(): "+ user.toUpperCase() +" executing task: " + task.getName());
			    taskService.start(task.getId(), user);
			    taskService.completeWithResults(task.getId(), user, map);	
			}
			if (debug) System.out.println("testValidPolicy(): Executing [startWeryfikacja]");
			assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
			if (debug) System.out.println("testValidPolicy(): Executing [Sprawdz polise]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz polise");
			if (debug) System.out.println("testValidPolicy(): Executing [Czy polisa wazna]");
			assertNodeTriggered(processInstance.getId(), "Czy polisa wazna");		
			if (debug) System.out.println("testValidPolicy(): Executing [Uzupelnij formularz danymi]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnij formularz danymi");
			if (debug) System.out.println("#############################################");
			if (info) System.out.println("STATUS: testValidPolicy() finished successful.");	
	}
	
	    @Test
	    public void testInvalidPolicy() {
			
			/* Set process variables */
			String user = "krisv";     // INFO: change it to test different path
			String cause = "damage";   // INFO: change it to test different path
			String policyId = "3";     // INFO: change it to test different path
			String settlementId = "1"; // (1: naprawa) INFO: change it to test different path

			if (mock){
			    UserSession userSession = new UserSessionImpl();
			    userSession.setUsername(user);
				
			    PowerMockito.mockStatic(ServiceRepository.class);
			    PowerMockito.mockStatic(jBPMNavigationHandler.class);
			
			    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
			}
			
			Map map = new HashMap();
			map.put("user",user);
			map.put("policyId", policyId);
			map.put("cause", cause);
			map.put("settlementId", settlementId);
			
			/* Create session and task service */
			if (debug) System.out.println("testInvalidPolicy(): Creating session and task service...");
			StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
			TaskService taskService = getTaskService(ksession);
			
			/* Start process */
			if (debug) System.out.println("testInvalidPolicy(): Starting process...");
			
			ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
			if (debug) System.out.println("testInvalidPolicy(): Executing [Start]");
			assertNodeTriggered(processInstance.getId(), "Start");
			if (debug) System.out.println("testInvalidPolicy(): Executing [Sprawdz ilosc polis]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
			if (debug) System.out.println("testInvalidPolicy(): Executing [Wiecej niz 1 polisa]");
			assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
			
			// Let user execute 'Polisy' 
			if("krisv".equals(user)){		
				if (debug) System.out.println("testInvalidPolicy(): Executing [Wybierz polise]");
			    assertNodeTriggered(processInstance.getId(), "Wybierz polise"); 
			    List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			    TaskSummary task = list.get(0);
			    if (debug) System.out.println("testInvalidPolicy(): "+ user.toUpperCase() +" executing task: " + task.getName());
			    taskService.start(task.getId(), user);
			    taskService.completeWithResults(task.getId(), user, map);	
			}
			if (debug) System.out.println("testInvalidPolicy(): Executing [startWeryfikacja]");
			assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
			if (debug) System.out.println("testInvalidPolicy(): Executing [Sprawdz polise]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz polise");
			if (debug) System.out.println("testInvalidPolicy(): Executing [Czy polisa wazna]");
			assertNodeTriggered(processInstance.getId(), "Czy polisa wazna");		
			if (debug) System.out.println("testInvalidPolicy(): Executing [Polisa niewazna]");
			assertNodeTriggered(processInstance.getId(), "Polisa niewazna");
			if (debug) System.out.println("#############################################");
			if (info) System.out.println("STATUS: testInvalidPolicy() finished successful.");
		}
	
	  @Test
      public void testRepair() {
			
			/* Set process variables */
			String user = "krisv";     // INFO: change it to test different path
			String cause = "damage";   // INFO: change it to test different path
			String policyId = "2";     // INFO: change it to test different path
			String settlementId = "1"; // (1: naprawa) INFO: change it to test different path

			if (mock){
			    UserSession userSession = new UserSessionImpl();
			    userSession.setUsername(user);
				
			    PowerMockito.mockStatic(ServiceRepository.class);
			    PowerMockito.mockStatic(jBPMNavigationHandler.class);
			
			    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
			}
			
			Map map = new HashMap();
			map.put("user",user);
			map.put("policyId", policyId);
			map.put("cause", cause);
			map.put("settlementId", settlementId);
			
			/* Create session and task service */
			if (debug) System.out.println("testRepair(): Creating session and task service...");
			StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
			TaskService taskService = getTaskService(ksession);
			
			/* Start process */
			if (debug) System.out.println("testRepair(): Starting process...");
			
			ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
			if (debug) System.out.println("testRepair(): Executing [Start]");
			assertNodeTriggered(processInstance.getId(), "Start");
			if (debug) System.out.println("testRepair(): Executing [Sprawdz ilosc polis]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
			if (debug) System.out.println("testRepair(): Executing [Wiecej niz 1 polisa]");
			assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
			
			// Let user execute 'Polisy' 
			if("krisv".equals(user)){		
				if (debug) System.out.println("testRepair(): Executing [Wybierz polise]");
			    assertNodeTriggered(processInstance.getId(), "Wybierz polise"); 
			    List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			    TaskSummary task = list.get(0);
			    if (debug) System.out.println("testRepair(): "+ user.toUpperCase() +" executing task: " + task.getName());
			    taskService.start(task.getId(), user);
			    taskService.completeWithResults(task.getId(), user, map);	
			}
			if (debug) System.out.println("testRepair(): Executing [startWeryfikacja]");
			assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
			if (debug) System.out.println("testRepair(): Executing [Sprawdz polise]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz polise");
			if (debug) System.out.println("testRepair(): Executing [Czy polisa wazna]");
			assertNodeTriggered(processInstance.getId(), "Czy polisa wazna");		
			if (debug) System.out.println("testRepair(): Executing [Uzupelnij formularz danymi]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnij formularz danymi");
				
			//Let user execute 'Szkoda' 
			if (debug) System.out.println("testRepair(): Executing [Uzupelnienie szczegolow szkody]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnienie szczegolow szkody"); // TODO: doesn't work???
			List<TaskSummary> list2 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			TaskSummary task2 = list2.get(0);
			if (debug) System.out.println("testRepair(): "+ user.toUpperCase() +" executing task: " + task2.getName());
		    taskService.start(task2.getId(), user);
		    taskService.completeWithResults(task2.getId(), user, map); // INFO: paramters required (for result mapping)
		    
		    if (debug) System.out.println("testAllianz(): Executing [Uszkodzenie?]");
		    assertNodeTriggered(processInstance.getId(), "Uszkodzenie?"); 
		   
		    //Let user execute 'damageDetails' 
		    if("damage".equals(cause)){
		    	if (debug) System.out.println("testRepair(): Executing [Uzupelnij szczegoly uszkodzenia]");
				assertNodeTriggered(processInstance.getId(), "Uzupelnij szczegoly uszkodzenia"); 
		 		List<TaskSummary> list3 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
		 		TaskSummary task3 = list3.get(0);
		 		if (debug) System.out.println("testRepair(): "+ user.toUpperCase() +" executing task: " + task3.getName());
		 	    taskService.start(task3.getId(), user);
		 	    taskService.complete(task3.getId(), user, null);
		    }
		    
		    if (debug) System.out.println("testRepair(): Executing [Umiesc dokumenty]");
		    assertNodeTriggered(processInstance.getId(), "Umiesc dokumenty");
			
		    //Let user execute 'Document Task' 
		    if (debug) System.out.println("testRepair(): Executing [Umiesc dokumenty]");
			assertNodeTriggered(processInstance.getId(), "Umiesc dokumenty"); 
	 		List<TaskSummary> list4 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
	 		TaskSummary task4 = list4.get(0);
	 		if (debug) System.out.println("testRepair(): "+ user.toUpperCase() +" executing task: " + task4.getName());
	 	    taskService.start(task4.getId(), user);
	 	    taskService.complete(task4.getId(), user, null);
			
	 	    if (debug) System.out.println("testRepair(): Executing [Stworzenie zgloszenia]");
		    assertNodeTriggered(processInstance.getId(), "Stworzenie zgloszenia");
		    if (debug) System.out.println("testRepair(): Executing [Zaproponuj porozumienie]");
		    assertNodeTriggered(processInstance.getId(), "Zaproponuj porozumienie");
		    
		    //Let user execute 'selectAgreement' 
		    if (debug) System.out.println("testRepair(): Executing [Wybierz porozumienie]");
			assertNodeTriggered(processInstance.getId(), "Wybierz porozumienie"); 
	 		List<TaskSummary> list5 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
	 		TaskSummary task5 = list5.get(0);
	 		if (debug) System.out.println("testRepair(): "+ user.toUpperCase() +" executing task: " + task5.getName());
	 	    taskService.start(task5.getId(), user);
	 	    taskService.completeWithResults(task5.getId(), user, map); // INFO: paramters required (for result mapping)
	 	      
		    if (debug) System.out.println("testRepair(): Rodzaj porozumienia");
		    assertNodeTriggered(processInstance.getId(), "Rodzaj porozumienia");
		    if (debug) System.out.println("testRepair(): Executing [Naprawa]");
		    assertNodeTriggered(processInstance.getId(), "Naprawa"); 
			if (debug) System.out.println("#############################################");
			if (info) System.out.println("STATUS: testRepair() finished successful.");
		}
	  
	  @Test
      public void testNoRepair() {
			
			/* Set process variables */
			String user = "krisv";     // INFO: change it to test different path
			String cause = "damage";   // INFO: change it to test different path
			String policyId = "2";     // INFO: change it to test different path
			String settlementId = "0"; // (1: naprawa) INFO: change it to test different path

			if (mock){
			    UserSession userSession = new UserSessionImpl();
			    userSession.setUsername(user);
				
			    PowerMockito.mockStatic(ServiceRepository.class);
			    PowerMockito.mockStatic(jBPMNavigationHandler.class);
			
			    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
			}
			
			Map map = new HashMap();
			map.put("user",user);
			map.put("policyId", policyId);
			map.put("cause", cause);
			map.put("settlementId", settlementId);
			
			/* Create session and task service */
			if (debug) System.out.println("testNoRepair(): Creating session and task service...");
			StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
			TaskService taskService = getTaskService(ksession);
			
			/* Start process */
			if (debug) System.out.println("testNoRepair(): Starting process...");
			
			ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
			if (debug) System.out.println("testNoRepair(): Executing [Start]");
			assertNodeTriggered(processInstance.getId(), "Start");
			if (debug) System.out.println("testNoRepair(): Executing [Sprawdz ilosc polis]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
			if (debug) System.out.println("testNoRepair(): Executing [Wiecej niz 1 polisa]");
			assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
			
			// Let user execute 'Polisy' 
			if("krisv".equals(user)){		
				if (debug) System.out.println("testNoRepair(): Executing [Wybierz polise]");
			    assertNodeTriggered(processInstance.getId(), "Wybierz polise"); 
			    List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			    TaskSummary task = list.get(0);
			    if (debug) System.out.println("testNoRepair(): "+ user.toUpperCase() +" executing task: " + task.getName());
			    taskService.start(task.getId(), user);
			    taskService.completeWithResults(task.getId(), user, map);	
			}
			if (debug) System.out.println("testNoRepair(): Executing [startWeryfikacja]");
			assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
			if (debug) System.out.println("testNoRepair(): Executing [Sprawdz polise]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz polise");
			if (debug) System.out.println("testNoRepair(): Executing [Czy polisa wazna]");
			assertNodeTriggered(processInstance.getId(), "Czy polisa wazna");		
			if (debug) System.out.println("testNoRepair(): Executing [Uzupelnij formularz danymi]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnij formularz danymi");
				
			//Let user execute 'Szkoda' 
			if (debug) System.out.println("testNoRepair(): Executing [Uzupelnienie szczegolow szkody]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnienie szczegolow szkody"); // TODO: doesn't work???
			List<TaskSummary> list2 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			TaskSummary task2 = list2.get(0);
			if (debug) System.out.println("testNoRepair(): "+ user.toUpperCase() +" executing task: " + task2.getName());
		    taskService.start(task2.getId(), user);
		    taskService.completeWithResults(task2.getId(), user, map); // INFO: paramters required (for result mapping)
		    
		    if (debug) System.out.println("testAllianz(): Executing [Uszkodzenie?]");
		    assertNodeTriggered(processInstance.getId(), "Uszkodzenie?"); 
		   
		    //Let user execute 'damageDetails' 
		    if("damage".equals(cause)){
		    	if (debug) System.out.println("testNoRepair(): Executing [Uzupelnij szczegoly uszkodzenia]");
				assertNodeTriggered(processInstance.getId(), "Uzupelnij szczegoly uszkodzenia"); 
		 		List<TaskSummary> list3 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
		 		TaskSummary task3 = list3.get(0);
		 		if (debug) System.out.println("testNoRepair(): "+ user.toUpperCase() +" executing task: " + task3.getName());
		 	    taskService.start(task3.getId(), user);
		 	    taskService.complete(task3.getId(), user, null);
		    }
		    
		    if (debug) System.out.println("testNoRepair(): Executing [Umiesc dokumenty]");
		    assertNodeTriggered(processInstance.getId(), "Umiesc dokumenty");
			
		    //Let user execute 'Document Task' 
		    if (debug) System.out.println("testNoRepair(): Executing [Umiesc dokumenty]");
			assertNodeTriggered(processInstance.getId(), "Umiesc dokumenty"); 
	 		List<TaskSummary> list4 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
	 		TaskSummary task4 = list4.get(0);
	 		if (debug) System.out.println("testNoRepair(): "+ user.toUpperCase() +" executing task: " + task4.getName());
	 	    taskService.start(task4.getId(), user);
	 	    taskService.complete(task4.getId(), user, null);
			
	 	   if (debug) System.out.println("testNoRepair(): Executing [Stworzenie zgloszenia]");
		    assertNodeTriggered(processInstance.getId(), "Stworzenie zgloszenia");
		    if (debug) System.out.println("testNoRepair(): Executing [Zaproponuj porozumienie]");
		    assertNodeTriggered(processInstance.getId(), "Zaproponuj porozumienie");
		    
		    //Let user execute 'selectAgreement' 
		    if (debug) System.out.println("testNoRepair(): Executing [Wybierz porozumienie]");
			assertNodeTriggered(processInstance.getId(), "Wybierz porozumienie"); 
	 		List<TaskSummary> list5 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
	 		TaskSummary task5 = list5.get(0);
	 		if (debug) System.out.println("testNoRepair(): "+ user.toUpperCase() +" executing task: " + task5.getName());
	 	    taskService.start(task5.getId(), user);
	 	    taskService.completeWithResults(task5.getId(), user, map); // INFO: paramters required (for result mapping)
	 	      
	 	    if (debug) System.out.println("testNoRepair(): Rodzaj porozumienia");
		    assertNodeTriggered(processInstance.getId(), "Rodzaj porozumienia");
		    if (debug) System.out.println("testNoRepair(): Executing [Brak naprawy]");
		    assertNodeTriggered(processInstance.getId(), "Brak naprawy"); 
		    if (debug) System.out.println("#############################################");
		    if (info) System.out.println("STATUS: testNoRepair() finished successful.");
		  
		}
	  
	  @Test
      public void testCauseDamage() {
			
			/* Set process variables */
			String user = "krisv";     // INFO: change it to test different path
			String cause = "damage";   // INFO: change it to test different path
			String policyId = "2";     // INFO: change it to test different path
			String settlementId = "1"; // (1: naprawa) INFO: change it to test different path

			if (mock){
			    UserSession userSession = new UserSessionImpl();
			    userSession.setUsername(user);
				
			    PowerMockito.mockStatic(ServiceRepository.class);
			    PowerMockito.mockStatic(jBPMNavigationHandler.class);
			
			    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
			}
			
			Map map = new HashMap();
			map.put("user",user);
			map.put("policyId", policyId);
			map.put("cause", cause);
			map.put("settlementId", settlementId);
			
			/* Create session and task service */
			if (debug) System.out.println("testCauseDamage(): Creating session and task service...");
			StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
			TaskService taskService = getTaskService(ksession);
			
			/* Start process */
			if (debug) System.out.println("testCauseDamage(): Starting process...");
			
			ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
			if (debug) System.out.println("testCauseDamage(): Executing [Start]");
			assertNodeTriggered(processInstance.getId(), "Start");
			if (debug) System.out.println("testCauseDamage(): Executing [Sprawdz ilosc polis]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
			if (debug) System.out.println("testCauseDamage(): Executing [Wiecej niz 1 polisa]");
			assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
			
			// Let user execute 'Polisy' 
			if("krisv".equals(user)){		
				if (debug) System.out.println("testCauseDamage(): Executing [Wybierz polise]");
			    assertNodeTriggered(processInstance.getId(), "Wybierz polise"); 
			    List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			    TaskSummary task = list.get(0);
			    if (debug) System.out.println("testCauseDamage(): "+ user.toUpperCase() +" executing task: " + task.getName());
			    taskService.start(task.getId(), user);
			    taskService.completeWithResults(task.getId(), user, map);	
			}
			if (debug) System.out.println("testCauseDamage(): Executing [startWeryfikacja]");
			assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
			if (debug) System.out.println("testCauseDamage(): Executing [Sprawdz polise]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz polise");
			if (debug) System.out.println("testCauseDamage(): Executing [Czy polisa wazna]");
			assertNodeTriggered(processInstance.getId(), "Czy polisa wazna");		
			if (debug) System.out.println("testCauseDamage(): Executing [Uzupelnij formularz danymi]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnij formularz danymi");
				
			//Let user execute 'Szkoda' 
			if (debug) System.out.println("testCauseDamage(): Executing [Uzupelnienie szczegolow szkody]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnienie szczegolow szkody"); // TODO: doesn't work???
			List<TaskSummary> list2 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			TaskSummary task2 = list2.get(0);
			if (debug) System.out.println("testCauseDamage(): "+ user.toUpperCase() +" executing task: " + task2.getName());
		    taskService.start(task2.getId(), user);
		    taskService.completeWithResults(task2.getId(), user, map); // INFO: paramters required (for result mapping)
		    
		    if (debug) System.out.println("testAllianz(): Executing [Uszkodzenie?]");
		    assertNodeTriggered(processInstance.getId(), "Uszkodzenie?"); 
		    if (debug) System.out.println("testCauseDamage(): Executing [Uzupelnij szczegoly uszkodzenia]");
		    assertNodeTriggered(processInstance.getId(), "Uzupelnij szczegoly uszkodzenia"); 	 	
			if (debug) System.out.println("#############################################");		
			if (info) System.out.println("STATUS: testCauseDamage() finished successful.");
	  }
	  
	  @Test
      public void testCauseTheft() {
			
			/* Set process variables */
			String user = "krisv";     // INFO: change it to test different path
			String cause = "theft";   // INFO: change it to test different path
			String policyId = "2";     // INFO: change it to test different path
			String settlementId = "1"; // (1: naprawa) INFO: change it to test different path

			if (mock){
			    UserSession userSession = new UserSessionImpl();
			    userSession.setUsername(user);
				
			    PowerMockito.mockStatic(ServiceRepository.class);
			    PowerMockito.mockStatic(jBPMNavigationHandler.class);
			
			    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
			}
			
			Map map = new HashMap();
			map.put("user",user);
			map.put("policyId", policyId);
			map.put("cause", cause);
			map.put("settlementId", settlementId);
			
			/* Create session and task service */
			if (debug) System.out.println("testCauseTheft(): Creating session and task service...");
			StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
			TaskService taskService = getTaskService(ksession);
			
			/* Start process */
			if (debug) System.out.println("testCauseTheft(): Starting process...");
			
			ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
			if (debug) System.out.println("testCauseTheft(): Executing [Start]");
			assertNodeTriggered(processInstance.getId(), "Start");
			if (debug) System.out.println("testCauseTheft(): Executing [Sprawdz ilosc polis]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
			if (debug) System.out.println("testCauseTheft(): Executing [Wiecej niz 1 polisa]");
			assertNodeTriggered(processInstance.getId(), "Wiecej niz 1 polisa");
			
			// Let user execute 'Polisy' 
			if("krisv".equals(user)){		
				if (debug) System.out.println("testCauseTheft(): Executing [Wybierz polise]");
			    assertNodeTriggered(processInstance.getId(), "Wybierz polise"); 
			    List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			    TaskSummary task = list.get(0);
			    if (debug) System.out.println("testCauseTheft(): "+ user.toUpperCase() +" executing task: " + task.getName());
			    taskService.start(task.getId(), user);
			    taskService.completeWithResults(task.getId(), user, map);	
			}
			if (debug) System.out.println("testCauseTheft(): Executing [startWeryfikacja]");
			assertNodeTriggered(processInstance.getId(), "startWeryfikacja");
			if (debug) System.out.println("testCauseTheft(): Executing [Sprawdz polise]");
			assertNodeTriggered(processInstance.getId(), "Sprawdz polise");
			if (debug) System.out.println("testCauseTheft(): Executing [Czy polisa wazna]");
			assertNodeTriggered(processInstance.getId(), "Czy polisa wazna");		
			if (debug) System.out.println("testCauseTheft(): Executing [Uzupelnij formularz danymi]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnij formularz danymi");
				
			//Let user execute 'Szkoda' 
			if (debug) System.out.println("testCauseTheft(): Executing [Uzupelnienie szczegolow szkody]");
			assertNodeTriggered(processInstance.getId(), "Uzupelnienie szczegolow szkody"); // TODO: doesn't work???
			List<TaskSummary> list2 = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
			TaskSummary task2 = list2.get(0);
			if (debug) System.out.println("testCauseTheft(): "+ user.toUpperCase() +" executing task: " + task2.getName());
		    taskService.start(task2.getId(), user);
		    taskService.completeWithResults(task2.getId(), user, map); // INFO: paramters required (for result mapping)
		    
		    if (debug) System.out.println("testCauseTheft(): Executing [Uszkodzenie?]");
		    assertNodeTriggered(processInstance.getId(), "Uszkodzenie?"); 
		    if (debug) System.out.println("testCauseTheft(): Executing [Umiesc dokumenty]");
		    assertNodeTriggered(processInstance.getId(), "Umiesc dokumenty"); 	    
			if (debug) System.out.println("#############################################");
			if (info) System.out.println("STATUS: testCauseTheft() finished successful.");
		}
	
	
	
	
	
	/* testUserAdmin:
	 * - user: admin
	 * - cause: damage
	 * - policyId: 2
	 * - settlementId: 1
	 */
	@Test
	public void testUnknownUser() {
		
		/* Set process variables */
		String user = "unknown";     // INFO: change it to test different path
		String cause = "damage";   // INFO: change it to test different path
		String policyId = "2";     // INFO: change it to test different path
		String settlementId = "1"; // (1: naprawa) INFO: change it to test different path

		if (mock){
		    UserSession userSession = new UserSessionImpl();
		    userSession.setUsername(user);
			
		    PowerMockito.mockStatic(ServiceRepository.class);
		    PowerMockito.mockStatic(jBPMNavigationHandler.class);
		
		    Mockito.when(ServiceRepository.getUserSessionInstance()).thenReturn(userSession);
		}
		
		Map map = new HashMap();
		map.put("user",user);
		map.put("policyId", policyId);
		map.put("cause", cause);
		map.put("settlementId", settlementId);
		
		/* Create session and task service */
		if (debug) System.out.println("testUserAdmin(): Creating session and task service...");
		StatefulKnowledgeSession ksession = createKnowledgeSession("Allianz.bpmn2");	
		TaskService taskService = getTaskService(ksession);
		
		/* Start process */
		if (debug) System.out.println("testUserAdmin(): Starting process...");
		
		ProcessInstance processInstance = ksession.startProcess("allianz.Allianz", map);		
		if (debug) System.out.println("testUserAdmin(): Executing [Start]");
		assertNodeTriggered(processInstance.getId(), "Start");
		if (debug) System.out.println("testUserAdmin(): Executing [Sprawdz ilosc polis]");
		assertNodeTriggered(processInstance.getId(), "Sprawdz ilosc polis");
		if (debug) System.out.println("testUserAdmin(): Executing [Brak polis]");
		assertNodeTriggered(processInstance.getId(), "Brak polis");
	    if (debug) System.out.println("testUserAdmin(): Finish succeeded");
	    assertNodeTriggered(processInstance.getId(), "End");

		// check whether the process instance has completed successfully
		assertProcessInstanceCompleted(processInstance.getId(), ksession);
	
		if (debug) System.out.println("#############################################");
		if (info) System.out.println("STATUS: testUserAdmin() finished successful.");
	}
	
	/*
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
	*/

	}
