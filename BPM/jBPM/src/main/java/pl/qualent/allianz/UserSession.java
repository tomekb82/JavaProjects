package pl.qualent.allianz;

public interface UserSession {

	public String getUsername();

	public void setUsername(String username);
	
	public String getResponse();

	public void setResponse(String response);
	
	public Long getProcessInstanceId();

	public void setProcessInstanceId(Long processInstanceId);
}
