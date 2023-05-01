package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {

    Optional<Player> findById(UUID uuid);
    List<Player> findByName(String Name);
    List<Player> findByGender(String Gender);
    List<Player> findByMainHand(String mainHand);
    List<Player> findBymainHandAndGender(String mainHand, String Gender);

    void deleteById(UUID uuid);

    List<Player> findAllByplayerIdIn(List<UUID> playerIds);
}
