package org.jpmh.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class SessionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String session;
    private String userName;
    private LocalDateTime timestamp;
}