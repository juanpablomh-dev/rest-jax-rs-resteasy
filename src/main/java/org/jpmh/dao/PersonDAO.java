package org.jpmh.dao;

import org.jpmh.model.Person;

import java.util.List;

public class PersonDAO extends CacheHelper<Long, Person> {
    public static final String NAME_CACHE_PERSONS = "exampleCachePersons";
    public static final int CONFIG_HEAP = 10;

    private static PersonDAO personDAO;
    private static Long sequence;

    private PersonDAO() {
        super(Long.class, Person.class, NAME_CACHE_PERSONS);
        initCacheHelper(CONFIG_HEAP);
        sequence = 0L;
    }

    public static PersonDAO getInstance() {
        if (personDAO == null) {
            personDAO = new PersonDAO();
        }
        return personDAO;
    }

    private static Long getSequence() {
        sequence++;
        return sequence;
    }

    public long addPerson(Person person) {
        person.setId(getSequence());
        this.add(person.getId(), person);
        return person.getId();
    }

    public Person getPerson(Long id) {
        return get(id);
    }

    public List<Person> getPersons() {
        return getAll();
    }

    public Person updatePerson(Person person) {
        return replace(person.getId(), person);
    }

    public void deletePerson(Long id) {
        delete(id);
    }
}
