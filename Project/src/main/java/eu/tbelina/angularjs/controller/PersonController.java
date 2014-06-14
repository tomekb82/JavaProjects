package eu.tbelina.angularjs.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.tbelina.angularjs.model.Person;
import eu.tbelina.angularjs.model.ResponseMessage;
import eu.tbelina.angularjs.service.impl.PersonService;
import eu.tbelina.spring.controller.HomeController;

/**
 * Controller for person actions.
 */
@Controller
public class PersonController {
 
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
    private PersonService personService;

    /*@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}*/
    
    private Map<Integer, Person> persons = new HashMap<Integer, Person>();
    
	@RequestMapping(value = "/person", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> getPersons() {
		persons.put(1, new Person(1, "Lionel", "Messi"));
        persons.put(2, new Person(2, "Cristiano", "Ronaldo"));
        
		System.out.println("PersonController:getPersons() 6666666666666666666666666666666666666666");
        return new ArrayList<Person>(persons.values());//personService.getAllPersons();
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addPerson(@RequestBody Person person) {
        if (person.getFirstName().length() <= 3 || person.getLastName().length() <= 3) {
            throw new IllegalArgumentException("moreThan3Chars");
        }
        personService.addPerson(person);
        return new ResponseMessage(ResponseMessage.Type.success, "personAdded");
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseMessage deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return new ResponseMessage(ResponseMessage.Type.success, "personDeleted");
    }

    @Autowired
    public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}
