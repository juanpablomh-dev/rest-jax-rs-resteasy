package org.jpmh.factory;

import org.jpmh.dto.SessionDTO;
import org.jpmh.model.Session;
import org.springframework.beans.BeanUtils;

public class BOFactorySession {

    public static Session getSession(SessionDTO sessionDTO) {
        if (sessionDTO != null) {
            Session session = new Session();
            BeanUtils.copyProperties(sessionDTO, session);
            return session;
        }
        return null;
    }

}
