package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Session;
import com.badminton.shuttlestats.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ClubService clubService;
    public SessionService(SessionRepository sessionRepository, ClubService clubService) {
        this.sessionRepository = sessionRepository;
        this.clubService = clubService;
    }

    public List<Session> getAllSessions() { return sessionRepository.findAll(); }

    public Optional<Session> getSessionBySession_SessionId(UUID sessionId) {
        return sessionRepository.findBySessionId(sessionId);
    }

    public Session saveSession(UUID clubId, Session session) {
        if (clubId == null || session == null) {
            throw new IllegalArgumentException();
        }

        if (!clubService.checkClubExistsById(clubId)) {
            throw new IllegalArgumentException();
        }

        session.setSessionId();

        return sessionRepository.save(session);
    }
}
