package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.Session;
import com.badminton.shuttlestats.model.keys.SessionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, SessionId> {
    public Optional<Session> findBySessionId(UUID sessionId);
}
