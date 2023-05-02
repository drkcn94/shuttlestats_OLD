package com.badminton.shuttlestats.repositories;

import com.badminton.shuttlestats.model.keys.ClubMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClubMemberRepository extends JpaRepository<com.badminton.shuttlestats.model.ClubMember, ClubMemberId> {
    List<com.badminton.shuttlestats.model.ClubMember> findByIdClubId(UUID clubId);

    Optional<com.badminton.shuttlestats.model.ClubMember> findByIdClubIdAndIdPlayerId(UUID clubId, UUID playerId);
    void deleteByIdClubId(UUID clubId);

    void deleteByIdPlayerId(UUID playerId);
}
