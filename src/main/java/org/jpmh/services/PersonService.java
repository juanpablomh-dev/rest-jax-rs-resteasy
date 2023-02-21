package org.jpmh.services;

import org.jpmh.dao.PersonDAO;
import org.jpmh.model.Person;

import java.util.List;

public class PersonService {
    private PersonDAO personDAO;

    public long addPerson(Person person) {
        personDAO = PersonDAO.getInstance();
        return personDAO.addPerson(person);
    }

    public Person getPerson(Long id) {
        personDAO = PersonDAO.getInstance();
        return personDAO.getPerson(id);
    }

    public List<Person> getPersons() {
        personDAO = PersonDAO.getInstance();
        return personDAO.getPersons();
    }

    public Person updatePerson(Person person) {
        personDAO = PersonDAO.getInstance();
        return personDAO.updatePerson(person);
    }

    public void deletePerson(Long id) {
        personDAO = PersonDAO.getInstance();
        personDAO.deletePerson(id);
    }
}
