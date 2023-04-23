package com.badminton.shuttlestats.model;

import com.badminton.shuttlestats.model.keys.SessionId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "session")
public class Session implements Serializable {

    @EmbeddedId
    private SessionId sessionId;

    @Column(name = "session_date")
    private Date sessionDate;

    @Column(name = "session_location")
    private String sessionLocation;

    public Session() {}

    public Session(Session session) {
        this.sessionId = session.getSessionId();
        this.sessionDate = session.getSessionDate();
        this.sessionLocation = session.getSessionLocation();
    }

    public Session(SessionId sessionId, Date sessionDate, String sessionLocation) {
        this.sessionId = sessionId;
        this.sessionDate = sessionDate;
        this.sessionLocation = sessionLocation;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    public void setSessionId(SessionId sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionLocation() {
        return sessionLocation;
    }

    public void setSessionLocation(String sessionLocation) {
        this.sessionLocation = sessionLocation;
    }
}
