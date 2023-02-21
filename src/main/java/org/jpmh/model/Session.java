package org.jpmh.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String session;
    private String userName;
    private LocalDateTime timestamp;

}
