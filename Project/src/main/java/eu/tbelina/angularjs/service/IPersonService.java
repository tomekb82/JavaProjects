package eu.tbelina.angularjs.service;

import java.util.List;

import eu.tbelina.angularjs.model.Person;

/**
 * Service to handle Persons.
 */
public interface IPersonService {
    List<Person> getAllPersons();

    void addPerson(Person person);

    void deletePerson(int id);
}
