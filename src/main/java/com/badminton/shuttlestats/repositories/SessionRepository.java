package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    public Optional<Session> findBySessionId(UUID sessionId);
}
