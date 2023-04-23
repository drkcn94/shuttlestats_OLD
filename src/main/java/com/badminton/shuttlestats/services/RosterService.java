package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.Roster;
import com.badminton.shuttlestats.repositories.RosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RosterService {
    @Autowired
    private RosterRepository rosterRepository;
    @Autowired
    private PlayerService playerService;

    public RosterService() {}

    public void savePlayerToRoster(UUID clubId, UUID playerId) {
        Roster roster = new Roster();
        roster.setId(clubId,playerId);
        roster.setJoinDate(LocalDate.now());
        rosterRepository.save(roster);
    }

    public List<Roster> findPlayersByClubId(UUID clubId) {
        return rosterRepository.findByIdClubId(clubId);
    }

    public Optional<Roster> findPlayerByClubIdAndPlayerId(UUID clubId, UUID playerId) {
        return rosterRepository.findByIdClubIdAndIdPlayerId(clubId,playerId);
    }

    public void deleteRosterByClubId(UUID clubId) {
        rosterRepository.deleteByIdClubId(clubId);
    }

    public void deleteRosterByPlayerId(UUID playerId) {
        rosterRepository.deleteByIdPlayerId(playerId);
    }
}
