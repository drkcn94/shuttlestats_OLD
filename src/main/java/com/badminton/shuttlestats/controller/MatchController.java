package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Match;
import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    public MatchController() {}

    @GetMapping("/")
    public ResponseEntity<List<Match>> getAllMatches() {
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

    @PostMapping("/session/match")
    public ResponseEntity<Match> addMatch(@RequestBody UUID sessionId, List<Player> playersInGame, int teamOneScore, int teamTwoScore) {
        try {
            Match savedMatch = this.matchService.saveMatch(sessionId, playersInGame, teamOneScore,teamTwoScore);
            return new ResponseEntity<>(savedMatch, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
