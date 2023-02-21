package org.jpmh.utils.mocks;

import com.github.javafaker.Faker;
import org.jpmh.model.Person;

public class MockPerson {
    private static final int INITIAL_AGE = 18;
    private static final int FINAL_AGE = 99;

    public static Person generateMockPerson() {
        Faker faker = new Faker();
        Person person = new Person();

        person.setName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setStreetAddress(faker.address().streetAddress());
        person.setCellPhone(faker.phoneNumber().cellPhone());
        person.setAge(faker.number().numberBetween(INITIAL_AGE, FINAL_AGE));
        return person;
    }
}
