package pl.qualent.allianz.diagram;

import java.util.ArrayList;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import pl.qualent.allianz.UserSession;
import pl.qualent.allianz.jBPMService;


@Named
@Scope("request")
public class DrawDiagramBean {

	@Autowired
	private jBPMService jBPMService;
	
	@Autowired
	private UserSession userSession;
	
	private ArrayList<String> coordinates;
	
	private String processInstaceId;
	
	public void getCoordinateArrayList() throws Exception{
		long processId = 0;
		try{
			processId = Long.parseLong(processInstaceId);
		}catch(NumberFormatException e ){
			processId = 0;
		}
		
		if(processId<=0){
			processInstaceId = userSession.getProcessInstanceId().toString();
		}
		coordinates = jBPMService.getProcessGraphicalStatus(processInstaceId);
	}


	public ArrayList<String> getCoordinates() {
		return coordinates;
	}


	public void setCoordinates(ArrayList<String> coordinates) {
		this.coordinates = coordinates;
	}


	public String getProcessInstaceId() {
		return processInstaceId;
	}


	public void setProcessInstaceId(String processInstaceId) {
		this.processInstaceId = processInstaceId;
	}
	
}