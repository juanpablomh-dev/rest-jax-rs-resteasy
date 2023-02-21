package org.jpmh.factory;

import org.jpmh.dto.PersonDTO;
import org.jpmh.model.Person;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BOFactoryPerson {

    public static List<Person> getListPerson(List<PersonDTO> listPersonDTO) {
        if (listPersonDTO != null) {
            List<Person> listPerson = listPersonDTO
                    .stream()
                    .map(BOFactoryPerson::getPerson)
                    .collect(Collectors.toCollection(ArrayList::new));
            return listPerson;
        }
        return null;
    }

    public static Person getPerson(PersonDTO personDTO) {
        if (personDTO != null) {
            Person person = new Person();
            BeanUtils.copyProperties(personDTO, person);
            return person;
        }
        return null;
    }
}
