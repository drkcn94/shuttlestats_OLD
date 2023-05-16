package com.badminton.shuttlestats.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;


@Entity
@Table(name = "session")
public class Session implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "session_id", columnDefinition = "BINARY(16")
    private UUID sessionId;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private Club club;

    @Column(name = "session_date")
    private Date sessionDate;

    @Column(name = "session_location")
    private String sessionLocation;

    @Column(name = "session_address")
    private String sessionAddress;

    @Column(name = "session_status")
    private String sessionStatus;

    @Column(name = "require_organizer_approval")
    private Boolean requireOrganizerApproval;

    public Session() {}

    public Session(Session session) {
        this.sessionId = session.getSessionId();
        this.sessionDate = session.getSessionDate();
        this.sessionLocation = session.getSessionLocation();
    }

    public Session(UUID sessionId, Date sessionDate, String sessionLocation) {
        this.sessionId = sessionId;
        this.sessionDate = sessionDate;
        this.sessionLocation = sessionLocation;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId() {
        this.sessionId = UUID.randomUUID();
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
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

    public String getSessionAddress() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
