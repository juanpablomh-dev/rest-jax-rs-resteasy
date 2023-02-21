package org.jpmh.factory;

import org.jpmh.dto.SessionDTO;
import org.jpmh.model.Session;
import org.springframework.beans.BeanUtils;

public class DTOFactorySession {
    public static SessionDTO getSessionDTO(Session session) {
        if (session != null) {
            SessionDTO sessionDTO = new SessionDTO();
            BeanUtils.copyProperties(session, sessionDTO);
            return sessionDTO;
        }
        return null;
    }
}
