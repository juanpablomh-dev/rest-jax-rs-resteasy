package org.jpmh.services;

import org.jpmh.dao.SessionDAO;
import org.jpmh.model.Session;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SessionService {
    private SessionDAO sessionDAO;
    private static final long TIME_OUT_SESSION = 5;

    public String addSession(Session session) {
        sessionDAO = SessionDAO.getInstance();
        return sessionDAO.addSession(session);
    }

    public Session getSession(String sessionToken) {
        sessionDAO = SessionDAO.getInstance();
        return sessionDAO.getSession(sessionToken);
    }

    public Session updateSession(Session session) {
        sessionDAO = SessionDAO.getInstance();
        return sessionDAO.updateSession(session);
    }

    public void deleteSession(String sessionToken) {
        sessionDAO = SessionDAO.getInstance();
        sessionDAO.deleteSession(sessionToken);
    }

    public boolean validateSession(String sessionToken) {
        Session session = sessionDAO.getSession(sessionToken);
        LocalDateTime now = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(session.getTimestamp(), now);
        return (minutes > TIME_OUT_SESSION) ? false : true;
    }
}
