package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.Roster;
import com.badminton.shuttlestats.services.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs/{clubId}/roster")
public class RosterController {

    @Autowired
    private RosterService rosterService;

    @GetMapping("/")
    public ResponseEntity<Player> getPlayersOfClub() {

    }

    @PostMapping("/addPlayer")
    public ResponseEntity<Roster> addPlayerToRoster(@PathVariable String clubId, Player player) {

    }

}
