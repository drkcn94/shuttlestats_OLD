package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Club;
import com.badminton.shuttlestats.model.Session;
import com.badminton.shuttlestats.repositories.SessionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final ClubService clubService;
    public SessionService(SessionRepository sessionRepository, ClubService clubService) {
        this.sessionRepository = sessionRepository;
        this.clubService = clubService;
    }

    public List<Session> getAllSessions() { return sessionRepository.findAll(); }

    public Session saveSession(UUID clubId, Session session) {
        if (clubId == null || session == null) {
            throw new IllegalArgumentException();
        }

        Optional<Club> toFind = clubService.getClubById(clubId);

        if (toFind.isEmpty()) {
            throw new IllegalArgumentException();
        }

        session.setSessionId(clubId);

        return new Session();
    }
}
