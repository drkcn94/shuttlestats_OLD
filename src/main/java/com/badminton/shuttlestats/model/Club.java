package com.badminton.shuttlestats.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue
    @Column(name = "club_id", columnDefinition = "BINARY(16)")
    private UUID clubId;
    @Column(name = "name")
    private String clubName;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "public_visibility")
    private Boolean publicVisibility;

    public Club() {}

    public Club(String clubName, Boolean publicVisibility) {
        this.clubId = UUID.randomUUID();
        this.clubName = clubName;
        this.creationDate = LocalDate.now();
        this.publicVisibility = publicVisibility;
    }

    public UUID getClubId() {
        return clubId;
    }

    public void setClubId(UUID clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getPublicVisibility() {
        return publicVisibility;
    }

    public void setPublicVisibility(Boolean publicVisibility) {
        this.publicVisibility = publicVisibility;
    }
}
