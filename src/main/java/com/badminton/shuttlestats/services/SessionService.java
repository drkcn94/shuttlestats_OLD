package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.repositories.SessionRepository;

public class SessionService {

    private final SessionRepository sessionRepository;
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
