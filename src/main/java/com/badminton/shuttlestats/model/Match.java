package com.badminton.shuttlestats.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "match")
public class Match implements Serializable {

    @EmbeddedId
    private UUID matchId;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    private Session session;

    @Column(name = "match_type")
    private String matchType;

    @Column(name = "player_one_id")
    private UUID playerOneId;

    @Column(name = "player_two_id")
    private UUID playerTwoId;

    @Column(name = "player_three_id")
    private UUID playerThreeId;

    @Column(name = "player_four_id")
    private UUID playerFourId;

    @Column(name = "team_one_score")
    private Integer teamOneScore;

    @Column(name = "team_two_score")
    private Integer teamTwoScore;

    public Match() {}
    public Match(Session session) {
        this.setMatchId(UUID.randomUUID());
        this.setSession(session);
    }
    public Match(Match match) {
        this.matchId = match.getMatchId();
        this.matchType = match.getMatchType();
        this.playerOneId = match.getPlayerOneId();
        this.playerTwoId = match.getPlayerTwoId();
        this.playerThreeId = match.getPlayerThreeId();
        this.playerFourId = match.getPlayerFourId();
        this.teamOneScore = match.getTeamOneScore();
        this.teamTwoScore = match.getTeamTwoScore();
    }

    public Match(UUID matchId, String matchType, UUID playerOneId, UUID playerTwoId, Integer teamOneScore, Integer teamTwoScore) {
        this.matchId = matchId;
        this.matchType = matchType;
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
    }

    public Match(UUID matchId, String matchType, UUID playerOneId, UUID playerTwoId, UUID playerThreeId, UUID playerFourId, Integer teamOneScore, Integer teamTwoScore) {
        this.matchId = matchId;
        this.matchType = matchType;
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.playerThreeId = playerThreeId;
        this.playerFourId = playerFourId;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public UUID getSessionId() {
        return session.getSessionId();
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public UUID getPlayerOneId() {
        return playerOneId;
    }

    public void setPlayerOneId(UUID playerOneId) {
        this.playerOneId = playerOneId;
    }

    public UUID getPlayerTwoId() {
        return playerTwoId;
    }

    public void setPlayerTwoId(UUID playerTwoId) {
        this.playerTwoId = playerTwoId;
    }

    public UUID getPlayerThreeId() {
        return playerThreeId;
    }

    public void setPlayerThreeId(UUID playerThreeId) {
        this.playerThreeId = playerThreeId;
    }

    public UUID getPlayerFourId() {
        return playerFourId;
    }

    public void setPlayerFourId(UUID playerFourId) {
        this.playerFourId = playerFourId;
    }

    public Integer getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(Integer teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public Integer getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(Integer teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }
}
