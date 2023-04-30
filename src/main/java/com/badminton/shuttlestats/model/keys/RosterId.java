package com.badminton.shuttlestats.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class RosterId implements Serializable {
    @Column(name = "club_id")
    private UUID clubId;
    @Column(name = "player_id")
    private UUID playerId;

    public RosterId() {}

    public RosterId(UUID clubId, UUID playerId) {
        this.clubId = clubId;
        this.playerId = playerId;
    }

    public UUID getClubId() {
        return clubId;
    }
    public void setClubId(UUID clubId) {
        this.clubId = clubId;
    }
    public UUID getPlayerId() {
        return playerId;
    }
    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RosterId rosterId = (RosterId) o;
        return Objects.equals(clubId, rosterId.clubId) && Objects.equals(playerId, rosterId.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, playerId);
    }
}
