package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Match;
import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.Session;
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
    public ResponseEntity<Match> addMatchToSession(@RequestBody Session session, List<Player> teamOnePlayers, List<Player> teamTwoPlayers, int teamOneScore, int teamTwoScore) {
        try {
            Match savedMatch = matchService.saveMatch(session, teamOnePlayers, teamTwoPlayers, teamOneScore,teamTwoScore);
            return new ResponseEntity<>(savedMatch, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/session={sessionId}/match={matchId}")
    public ResponseEntity<Match> updateMatch(@PathVariable UUID sessionId, @PathVariable UUID matchId, @RequestBody List<Player> teamOnePlayers, List<Player> teamTwoPlayers, int teamOneScore, int teamTwoScore) {
        try {
            Match updatedMatch = matchService.updateMatch(sessionId, matchId, teamOnePlayers, teamTwoPlayers, teamOneScore, teamTwoScore);
            return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/session={sessionId}/match={matchId}")
    public ResponseEntity<Match> deleteMatch(@PathVariable String sessionId, String matchId) {
        UUID sessionIdToFind = UUID.fromString(sessionId);
        UUID matchIdToFind = UUID.fromString(matchId);

        matchService.deleteMatchById(UUID.fromString(matchId));
        return ResponseEntity.noContent().build();
    }
}
