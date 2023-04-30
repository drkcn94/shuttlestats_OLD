package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Club;
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
    @Autowired
    private ClubService clubService;

    public RosterService() {}


    // TO COMPLETE
    public void savePlayerToRoster(String clubId, String playerId) {
        Roster roster = new Roster(UUID.fromString(clubId), UUID.fromString(playerId));
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
