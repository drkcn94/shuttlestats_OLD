package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.ClubMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubMemberService {
    @Autowired
    private com.badminton.shuttlestats.repositories.ClubMember rosterRepository;
    @Autowired
    private PlayerService playerService;

    public ClubMemberService() {}


     //TO COMPLETE
    public void savePlayerToRoster(String clubId, String playerId) {
        ClubMember roster = new ClubMember(UUID.fromString(clubId), UUID.fromString(playerId));
        rosterRepository.save(roster);
    }

    public List<ClubMember> findPlayersByClubId(UUID clubId) {
        return rosterRepository.findByIdClubId(clubId);
    }

    public Optional<ClubMember> findPlayerByClubIdAndPlayerId(UUID clubId, UUID playerId) {
        return rosterRepository.findByIdClubIdAndIdPlayerId(clubId,playerId);
    }

    public void deleteRosterByClubId(UUID clubId) {
        rosterRepository.deleteByIdClubId(clubId);
    }

    public void deleteRosterByPlayerId(UUID playerId) {
        rosterRepository.deleteByIdPlayerId(playerId);
    }
}
