package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.Roster;
import com.badminton.shuttlestats.services.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/club={clubId}/roster")
public class RosterController {

    @Autowired
    private RosterService rosterService;

    @GetMapping("/")
    public ResponseEntity<List<Player>> getPlayersOfClub() {
        return new ResponseEntity<List<Player>>(HttpStatus.OK);
    }

    @PostMapping("/addPlayer={playerId}")
    public ResponseEntity<Roster> addPlayerToClubRoster(@PathVariable String clubId, @PathVariable String playerId) {
        rosterService.savePlayerToRoster(clubId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteRoster")
    public ResponseEntity<Void> deleteRoster(@PathVariable String clubId){
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deletePlayer")
    public ResponseEntity<Void> deletePlayerFromRoster(@PathVariable String clubId, Player player) {
        return ResponseEntity.noContent().build();
    }

}
