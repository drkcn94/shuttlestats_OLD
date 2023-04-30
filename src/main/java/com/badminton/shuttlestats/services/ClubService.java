package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Club;
import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.Roster;
import com.badminton.shuttlestats.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubService {
    @Autowired
    private final ClubRepository clubRepository;
    @Autowired
    private final PlayerService playerService;
    @Autowired
    private final RosterService rosterService;

    public ClubService(ClubRepository clubRepository, PlayerService playerService, RosterService rosterService) {
        this.clubRepository = clubRepository;
        this.playerService = playerService;
        this.rosterService = rosterService;
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
    public Optional<Club> getClubById(UUID clubId) {
        return clubRepository.findById(clubId);
    }
    public Club saveClub(Club clubDetails) {
        Club toSave = new Club(clubDetails.getClubName(), clubDetails.getPublicVisibility());
        clubRepository.save(toSave);
        return toSave;
    }

    public void deleteClubById(UUID clubId) {
        clubRepository.deleteById(clubId);
    }
}
