package pl.qualent.allianz.claim;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.jBPMService;


@Named
@Scope("session")
public class ClaimBean {
	
	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private Claim claim;
	
	@Autowired
	private UserSession userSession;
	
	public Claim getClaim(){
		return this.claim;
	}
	public void setClaim(Claim claim){
		this.claim = claim;
	}
	public void loadClaim(){		
		long taskId = jBPMService.getFirstTaskForUser(userSession.getUsername(), userSession.getProcessInstanceId()).getId();
		Map<?,?> content = (Map) jBPMService.getTaskContent(taskId);
		System.out.println("Ustawiam paramtery" + content.get("firstName"));
		Claim claimDTO = (Claim)content.get("claimDTO");
		if(claimDTO!=null){
			claim.setData(claimDTO);
		}
	}
	
	private static Map<String,Object> cause2Value;
	static{
		cause2Value = new LinkedHashMap<String,Object>();
		cause2Value.put("---- Wybierz ----", ""); 
		cause2Value.put("uszkodzenie pojazdu", "damage");
		cause2Value.put("kradzież wyposażenia", "theft");
	}
	private static Map<String,Object> applicant2Value;
	static{
		applicant2Value = new LinkedHashMap<String,Object>();
		applicant2Value.put("Poszkodowany", 1); //label, value
		applicant2Value.put("Pełnomocnik", 2);		
	}
	private static Map<String,Object> victim2Value;
	static{
		victim2Value = new LinkedHashMap<String,Object>();
		victim2Value.put("Osoba fizyczna", 1); //label, value
		victim2Value.put("Firma", 2);		
	}
	private static Map<String,Object> citizenship2Value;
	static{
		citizenship2Value = new LinkedHashMap<String,Object>();
		citizenship2Value.put("Polskie", 1); //label, value
		citizenship2Value.put("Inne", 2);		
	}
	private static Map<String,Object> sex2Value;
	static{
		sex2Value = new LinkedHashMap<String,Object>();
		sex2Value.put("Kobieta", 1); //label, value
		sex2Value.put("Mężczyzna", 2);		
	}
	private static Map<String,Object> country2Value;
	static{
		country2Value = new LinkedHashMap<String,Object>();
		country2Value.put("Polska", 1); //label, value
		country2Value.put("Inny", 2);		
	}
	public Map<String,Object> getCause2Value() {
		return cause2Value;
	}
	public Map<String,Object> getApplicant2Value() {
		return applicant2Value;
	}

	public Map<String,Object> getVictim2Value() {
		return victim2Value;
	}	
	public Map<String,Object> getCitizenship2Value() {
		return citizenship2Value;
	}
	
	public Map<String,Object> getSex2Value() {
		return sex2Value;
	}
	public Map<String,Object> getCountry2Value() {
		return country2Value;
	}

	public void submit(){	
		Map<String,Object> results = new HashMap<String,Object>();
		results.put("cause", claim.cause);
		long taskId = jBPMService.getFirstTaskForUser(userSession.getUsername(), userSession.getProcessInstanceId()).getId();
		this.claim = new Claim();
		jBPMService.completeTaskWithResults(taskId, userSession.getUsername(), results);
	}
	
	public void onLoad(){	//TODO Pomocnicza metoda - do usunięcia
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user", userSession.getUsername());
		jBPMService.startProcess("allianz.ClaimsDamage", params);
	}
	
}