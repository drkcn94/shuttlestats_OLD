package com.badminton.shuttlestats.model.keys;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Embeddable
public class SessionId implements Serializable {

    @GeneratedValue
    private UUID sessionId;
    private UUID clubId;

    public SessionId() {};

    public SessionId(SessionId sessionId) {
        this.sessionId = sessionId.getSessionId();
        this.clubId = sessionId.getClubId();
    }

    public SessionId(UUID sessionId, UUID clubId) {
        this.sessionId = sessionId;
        this.clubId = clubId;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public UUID getClubId() {
        return clubId;
    }

    public void setClubId(UUID clubId) {
        this.clubId = clubId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionId sessionId1 = (SessionId) o;
        return Objects.equals(sessionId, sessionId1.sessionId) && Objects.equals(clubId, sessionId1.clubId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, clubId);
    }
}
