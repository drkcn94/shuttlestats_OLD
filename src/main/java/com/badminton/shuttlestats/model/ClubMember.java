package com.badminton.shuttlestats.model;

import com.badminton.shuttlestats.model.keys.ClubMemberId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roster")
public class ClubMember implements Serializable {

    @EmbeddedId
    private ClubMemberId id;
    @Column(name = "join_date")
    private LocalDate joinDate;
    @ManyToOne
    @MapsId("clubId")
    @JoinColumn(name = "club_id")
    private Club club;
    @ManyToMany
    @JoinTable(
            name = "roster_player",
            joinColumns = {
                    @JoinColumn(name = "club_id", referencedColumnName = "club_id"),
                    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
            },
            inverseJoinColumns = @JoinColumn(name = "roster_id")
    )
    private Set<Player> players;

    public ClubMember() {}

    public ClubMember(ClubMemberId rosterId, LocalDate joinDate) {
        this.id = rosterId;
        this.joinDate = joinDate;
    }

    public ClubMember(UUID clubId, UUID playerId){
        this.id = new ClubMemberId(clubId, playerId);
        this.joinDate = LocalDate.now();
    }

    public ClubMember(Club club, Player player, LocalDate joinDate) {
        this.id = new ClubMemberId(club.getClubId(), player.getPlayerId());
        this.club = club;
        this.players = new HashSet<>();
        this.players.add(player);
        this.joinDate = joinDate;
    }

    public UUID getClubId() {
        return id.getClubId();
    }

    public UUID getPlayerId() { return id.getPlayerId(); }

    public Club getClub() { return club; }

    public void setId(UUID clubId, UUID playerId) {
        id.setClubId(clubId);
        id.setPlayerId(playerId);
    }

    public void setId(ClubMemberId rosterId) {
        this.id = rosterId;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
}
