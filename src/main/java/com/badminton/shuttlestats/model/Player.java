package com.badminton.shuttlestats.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "player_id", columnDefinition = "BINARY(16)")
    private UUID playerId;

    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "main_hand")
    private String mainHand;

    public Player() {}

    public Player(Player player) {
        this.name = player.getPlayerName();
        this.gender = player.getPlayerGender();
        this.mainHand = player.getPlayerMainHand();
    }

    public Player(String Name, String Gender, String MainHand) {
        this.playerId = UUID.randomUUID();
        this.name = Name;
        this.gender = Gender;
        this.mainHand = MainHand;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String playerName) {
        this.name = playerName;
    }

    public String getPlayerGender() {
        return gender;
    }

    public void setPlayerGender(String playerGender) {
        this.gender = playerGender;
    }

    public String getPlayerMainHand() {
        return mainHand;
    }

    public void setPlayerMainHand(String playerMainHand) {
        this.mainHand = playerMainHand;
    }
}

