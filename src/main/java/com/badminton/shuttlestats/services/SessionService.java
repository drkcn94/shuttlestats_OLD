package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Session;
import com.badminton.shuttlestats.repositories.SessionRepository;

import java.util.List;
import java.util.UUID;

public class SessionService {

    private final SessionRepository sessionRepository;
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getAllSessions() { return sessionRepository.findAll(); }

    public Session saveSession(UUID clubUUID, Session session) {

        return new Session();
    }
}
