package org.jpmh.factory;

import org.jpmh.dto.PersonDTO;
import org.jpmh.model.Person;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DTOFactoryPerson {
    public static List<PersonDTO> getListPersonDTO(List<Person> listPerson) {
        if (listPerson != null) {
            List<PersonDTO> listPersonDTO = listPerson
                    .stream()
                    .map(DTOFactoryPerson::getPersonDTO)
                    .collect(Collectors.toCollection(ArrayList::new));
            return listPersonDTO;
        }
        return null;
    }

    public static PersonDTO getPersonDTO(Person person) {
        if (person != null) {
            PersonDTO personDTO = new PersonDTO();
            BeanUtils.copyProperties(person, personDTO);
            return personDTO;
        }
        return null;
    }
}
