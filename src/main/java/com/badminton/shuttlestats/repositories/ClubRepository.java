package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClubRepository extends JpaRepository<Club, UUID> {
    Club findByClubId(UUID clubId);
}
