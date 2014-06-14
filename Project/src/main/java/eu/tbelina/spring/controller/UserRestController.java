package eu.tbelina.spring.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.tbelina.spring.model.UserRest;

@Controller
@RequestMapping(value = "/userRest")
public class UserRestController {

	private final  Map<Integer, UserRest> allUsers = new HashMap<Integer, UserRest>();
	 
	/* 
	 * GET
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<UserRest> getUsers() {
		System.out.println(" get all Users");
		Collection<UserRest> users = allUsers.values();
	 
		return new ArrayList<UserRest>(users);
	}
	
	/* 
	 * GET
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody UserRest getUser(@PathVariable final int id) {
	
		for(Integer key: allUsers.keySet()){
		   if(key==id){
			   return allUsers.get(key);
		   }
		}
		return null;
	}
	/*
	 * POST
	 */
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody UserRest addUser(@RequestBody UserRest user) {
	   
		System.out.println("adding user" + user.toString());
	    user.setId(allUsers.size());
	    for(Integer key: allUsers.keySet()){
	    	if(allUsers.get(key).getName().equals(user.getName()))
	    		return user;
	    }
	    allUsers.put(user.getId(), user);
	    return user;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteUser(@PathVariable final int id){
	   for(Integer key: allUsers.keySet()){
		   if(key == id){
			   System.out.println("deleting user" + allUsers.get(key).toString());
			   allUsers.remove(key);
		   }
	   }
	}
   
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateUser(@PathVariable final int id, @RequestBody final UserRest user){
	   for(Integer key: allUsers.keySet()){
		   if(key == id){
			   System.out.println("updating user" + allUsers.get(key).toString());
			   if(!allUsers.get(key).getName().equals(user.getName()))
				   allUsers.put(key, user);
		   }
	   }
	}
   
   /*
   public UserRest findUserByName(final String name) {}
   */
}
