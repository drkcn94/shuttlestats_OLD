package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.Roster;
import com.badminton.shuttlestats.services.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs/{clubId}/roster")
public class RosterController {

    @Autowired
    private RosterService rosterService;

    @GetMapping("/")
    public ResponseEntity<List<Player>> getPlayersOfClub() {
        return new ResponseEntity<List<Player>>(HttpStatus.OK);
    }

    @PostMapping("/addPlayer")
    public ResponseEntity<Roster> addPlayerToRoster(@PathVariable String clubId, Player player) {
        return new ResponseEntity<Roster>(HttpStatus.OK);
    }



}
