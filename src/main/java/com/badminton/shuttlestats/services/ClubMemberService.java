package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.ClubMember;
import com.badminton.shuttlestats.repositories.ClubMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubMemberService {
    private final ClubMemberRepository clubMemberRepository;
    @Autowired
    private final PlayerService playerService;

    public ClubMemberService(ClubMemberRepository clubMemberRepository, PlayerService playerService) {
        this.clubMemberRepository = clubMemberRepository;
        this.playerService = playerService;
    }

     //TO COMPLETE
    public void savePlayerToRoster(String clubId, String playerId) {
        ClubMember roster = new ClubMember(UUID.fromString(clubId), UUID.fromString(playerId));
        clubMemberRepository.save(roster);
    }

    public List<ClubMember> findPlayersByClubId(UUID clubId) {
        return clubMemberRepository.findByIdClubId(clubId);
    }

    public Optional<ClubMember> findPlayerByClubIdAndPlayerId(UUID clubId, UUID playerId) {
        return clubMemberRepository.findByIdClubIdAndIdPlayerId(clubId,playerId);
    }

    public void deleteRosterByClubId(UUID clubId) {
        clubMemberRepository.deleteByIdClubId(clubId);
    }

    public void deleteRosterByPlayerId(UUID playerId) {
        clubMemberRepository.deleteByIdPlayerId(playerId);
    }
}
