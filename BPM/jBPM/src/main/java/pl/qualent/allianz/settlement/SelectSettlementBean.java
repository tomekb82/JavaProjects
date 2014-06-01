package pl.qualent.allianz.settlement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.jBPMService;


@Named
@Scope("request")
public class SelectSettlementBean {

	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private UserSession userSession;
	
	private Integer settlementId;
	
	public void submit(){	
		String user = userSession.getUsername();
		long taskId = jBPMService.getFirstTaskForUser(user, userSession.getProcessInstanceId()).getId();
		Map data = new HashMap();
		data.put("settlementId", settlementId.toString());
		jBPMService.completeTaskWithResults(taskId, user, data);
	}
	
	private static Map<String,Integer> settlements;
	
	static{
		settlements = new LinkedHashMap<String,Integer>();
		settlements.put("Naprawa", 1); //label, value
		settlements.put("Inne", 2);
	}

	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	public Map<String, Integer> getSettlements() {
		return settlements;
	}

	public void setSettlements(Map<String, Integer> settlements) {
		SelectSettlementBean.settlements = settlements;
	}

	public Integer getRandomNumber(){
		Random rand = new Random();
		return rand.nextInt(3333) + 123;
	}
	
}