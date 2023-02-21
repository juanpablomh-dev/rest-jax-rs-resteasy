package org.jpmh.services;

import org.jpmh.exception.InvalidSessionException;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class SecurityService {
    public void validateSessionToken(String sessionToken) throws InvalidSessionException {
        if (sessionToken == null || sessionToken.equals("INVALID_SESSION_TOKEN")) {
            throw new InvalidSessionException("Invalid SessionToken: " + sessionToken);
        }
    }
}