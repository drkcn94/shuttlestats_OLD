package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
