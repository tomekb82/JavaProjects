package eu.tbelina.angularjs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.tbelina.angularjs.model.Person;
import eu.tbelina.angularjs.service.IPersonService;

public class PersonService implements IPersonService {
    private Map<Integer, Person> persons = new HashMap<Integer, Person>();

    public PersonService() {
        persons.put(1, new Person(1, "Lionel", "Messi"));
        persons.put(2, new Person(2, "Cristiano", "Ronaldo"));
    }

    public List<Person> getAllPersons() {
    	System.out.println("PersonService: getAllPersons() pppppppppppppppppppppppppppppppppppppp");
        return new ArrayList<Person>(persons.values());
    }

    public void addPerson(Person person) {
        int id = 1;
        while (persons.get(id) != null) {
            id++;
        }
        person.setId(id);
        persons.put(id, person);
    }

    public void deletePerson(int id) {
        persons.remove(id);
    }
}
