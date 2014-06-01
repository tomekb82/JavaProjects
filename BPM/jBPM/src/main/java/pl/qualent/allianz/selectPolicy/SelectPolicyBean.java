package pl.qualent.allianz.selectPolicy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.jBPMService;


@Named
@Scope("request")
public class SelectPolicyBean {

	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private UserSession userSession;
	
	public void submit(){	
		String user = userSession.getUsername();
		long taskId = jBPMService.getFirstTaskForUser(user, userSession.getProcessInstanceId()).getId();
		Map data = new HashMap();
		data.put("policyId", policy.toString());
		jBPMService.completeTaskWithResults(taskId, user, data);
	}

	public Integer policy;
	
	private static Map<String,Integer> policies;
	
	static{
		policies = new LinkedHashMap<String,Integer>();
		policies.put("Polisa nr 1234567", 1); //label, value
		policies.put("Polisa nr 2345678", 2);
		policies.put("Polisa nr 3456798", 3);
	}

	public Integer getPolicy() {
		return policy;
	}

	public void setPolicy(Integer policy) {
		this.policy = policy;
	}

	public Map<String, Integer> getPolicies() {
		return policies;
	}

	public void setPolicies(Map<String, Integer> policies) {
		SelectPolicyBean.policies = policies;
	}

	
}