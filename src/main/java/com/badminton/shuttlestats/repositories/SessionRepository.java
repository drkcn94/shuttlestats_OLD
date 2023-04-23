package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
}
