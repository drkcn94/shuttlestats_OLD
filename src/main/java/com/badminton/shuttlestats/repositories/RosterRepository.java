package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.Roster;
import com.badminton.shuttlestats.model.keys.RosterId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RosterRepository extends JpaRepository<Roster, RosterId> {
    List<Roster> findByIdClubId(UUID clubId);

    Optional<Roster> findByIdClubIdAndIdPlayerId(UUID clubId, UUID playerId);
    void deleteByIdClubId(UUID clubId);

    void deleteByIdPlayerId(UUID playerId);
}
