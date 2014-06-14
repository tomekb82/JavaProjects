package eu.tbelina.rest.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import eu.tbelina.spring.model.UserRest;

@Component(value = "userRestClient")
public class UserRestClient {

	private final static String userServiceUrl = "http://localhost:8080/spring/userRest/";
	
	// Inject RestTemplate 
	private RestTemplate restTemplate;

	public UserRest getUser(final int id) {
		return this.restTemplate.getForObject(userServiceUrl + "{id}",
				UserRest.class, id);
	}
   
	public UserRest getUserWithHeaders(final int id) {
	      
		final ResponseEntity<UserRest> responseEntity = this.restTemplate
				.getForEntity(userServiceUrl + "{id}", UserRest.class, id);

		System.out.println("Response Status : " + responseEntity.getStatusCode());

		final HttpHeaders headers = responseEntity.getHeaders();
		System.out.println("headers in response are : " + headers);
		
		return responseEntity.getBody();
	}
   
	public UserRest[] getAllUsers() {
		return this.restTemplate.getForObject(userServiceUrl,
				UserRest[].class);
	}
   
	public UserRest createUser(final UserRest user) {
		return this.restTemplate.postForObject(userServiceUrl, user, UserRest.class);
		}
   
	public void updateUser(final UserRest user) {
		this.restTemplate.put(userServiceUrl + "{id}", user, user.getId());
	}

	public void deleteUser(final int id) {
		this.restTemplate.delete(userServiceUrl + "{id}", id);
	}

	/* Main function */
	public static void main(final String[] args) {
	   	   
		final ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"rest_client.xml");
		final UserRestClient restClient = (UserRestClient) appContext
	            .getBean("userRestClient");
	  
		// POST - add new user
	    UserRest newUser = new UserRest();
	    newUser.setName("Robin");
	    newUser.setAge(21);	    
	    newUser = restClient.createUser(newUser);
	    System.out.println("User created with name= " + newUser.getName());
	    UserRest newUser2 = new UserRest();
	    newUser2.setName("BBBB");
	    newUser2.setAge(31);	    
	    newUser2 = restClient.createUser(newUser2);
	    System.out.println("User created with name= " + newUser2.getName());
	    
		// GET single user
		UserRest user = restClient.getUser(0);
	    System.out.println(user.getName() + " is of age " + user.getAge());
	    // GET single user with headers
	    user = restClient.getUserWithHeaders(1);
	    System.out.println(user.getName() + " is of age " + user.getAge());
	            
	    /* PUT */
	    UserRest changedUser = newUser;
	    changedUser.setId(1);
	    changedUser.setName("Batman");
	    restClient.updateUser(changedUser);
	    
	    // Get all users
	    for(UserRest ur: restClient.getAllUsers()){
	    	System.out.println(ur.toString());
	    }
	    
	    /* DELETE */
	    restClient.deleteUser(1);
	    for(UserRest ur: restClient.getAllUsers()){
	    	System.out.println(ur.toString());
	    }	      
   } 

    public RestTemplate getRestTemplate() {
		return restTemplate;
		}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
