package org.jpmh.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
}