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
    private ClubRepository clubRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private RosterService rosterService;

    public ClubService() {}

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

    public Club updateClub(Club clubDetails) {
        if(clubDetails == null) {
            throw new IllegalArgumentException();
        }

        Optional<Club> toFind = clubRepository.findById(clubDetails.getClubId());

        if(toFind.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return clubRepository.save(clubDetails);
    }

    public void deleteClubById(UUID clubId) {
        clubRepository.deleteById(clubId);
    }
}
