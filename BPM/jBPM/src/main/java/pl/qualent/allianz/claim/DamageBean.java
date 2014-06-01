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
public class DamageBean {
	
	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private Damage damage;
	
	@Autowired
	private UserSession userSession;
	
	
	private static Map<String,Object> vehicle2Value;
	static{
		vehicle2Value = new LinkedHashMap<String,Object>();
		vehicle2Value.put("Samochód", 1); 
		vehicle2Value.put("Przyczepa/naczepa", 2);
		vehicle2Value.put("Jednoślad", 3);
		vehicle2Value.put("Pojazd szynowy", 4);
	}
	private static Map<String,Object> vehicleType2Value;
	static{
		vehicleType2Value = new LinkedHashMap<String,Object>();
		vehicleType2Value.put("Osobowy", 1); 
		vehicleType2Value.put("Ciężarowy", 2);
		vehicleType2Value.put("Dostawczy", 3);
		vehicleType2Value.put("Autobus", 4);
		vehicleType2Value.put("Pojazd specjalny", 5);
	}

	public void setDamage(Damage damage){
		this.damage = damage;
	}
	public Damage getDamage(){
		return this.damage;
	}
	
	public Map<String,Object> getVehicle2Value() {
		return vehicle2Value;
	}
	
	public Map<String,Object> getVehicleType2Value() {
		return vehicleType2Value;
	}
	
	public void submit(){	
		String user = userSession.getUsername();
		long taskId = jBPMService.getFirstTaskForUser(user, userSession.getProcessInstanceId()).getId();
		this.damage = new Damage();
		jBPMService.completeTask(taskId, user);
	}
}