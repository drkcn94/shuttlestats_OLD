package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.Session;
import com.badminton.shuttlestats.model.keys.SessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, SessionId> {
}
