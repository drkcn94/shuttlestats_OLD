package com.badminton.shuttlestats.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class MatchId implements Serializable {
    @Column(name = "match_id")
    private UUID matchId;
    @Column(name = "session_id")
    private UUID sessionId;

    public MatchId() {};

    public MatchId(UUID matchId, UUID sessionId) {
        this.matchId = matchId;
        this.sessionId = sessionId;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchId matchId1 = (MatchId) o;

        if (!matchId.equals(matchId1.matchId)) return false;
        return sessionId.equals(matchId1.sessionId);
    }

    @Override
    public int hashCode() {
        int result = matchId.hashCode();
        result = 31 * result + sessionId.hashCode();
        return result;
    }
}
