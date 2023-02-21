package org.jpmh.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PersonDTO extends ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String lastName;
    private String streetAddress;
    private int age;
    private String cellPhone;
    private String email;
}