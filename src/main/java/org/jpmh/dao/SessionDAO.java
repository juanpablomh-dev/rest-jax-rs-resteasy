package org.jpmh.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.jpmh.model.Session;

import java.util.List;

public class SessionDAO extends CacheHelper<String, Session> {
    public static final int CONFIG_HEAP = 100;
    public static final String NAME_CACHE_SESSION = "exampleCacheSessions";
    public static final String KEY_SESSION = "example_key_9999";
    private static final String REGEX_KEY_NAME = "[^a-zA-Z0-9]+" ;

    private static SessionDAO sessionDAO;

    private SessionDAO() {
        super(String.class, Session.class, NAME_CACHE_SESSION);
        initCacheHelper(CONFIG_HEAP);
    }

    public static SessionDAO getInstance() {
        if (sessionDAO == null) {
            sessionDAO = new SessionDAO();
        }
        return sessionDAO;
    }

    private static String getKey(String userName) {
        String userWithoutSpecialCharacters = userName.replaceAll(REGEX_KEY_NAME, "");
        return userWithoutSpecialCharacters + RandomStringUtils.randomAlphanumeric(12);
    }

    public String addSession(Session session) {
        String sessionToken = getKey(session.getUserName());
        this.add(sessionToken, session);
        return sessionToken;
    }

    public Session getSession(String sessionToken) {
        return get(sessionToken);
    }

    public List<Session> getSessions() {
        return getAll();
    }

    public Session updateSession(Session session) {
        return replace(session.getSession(), session);
    }

    public void deleteSession(String sessionToken) {
        delete(sessionToken);
    }
}
