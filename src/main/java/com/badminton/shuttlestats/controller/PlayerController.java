package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.services.PlayerService;
import com.badminton.shuttlestats.services.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private RosterService rosterService;

    public PlayerController() {}

    @GetMapping("/")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerService.findAllPlayers(), HttpStatus.OK);
    }

    // Look into PlayerService Implementation that uses criteria to find players instead of this function
    // that may have many permutations
    @GetMapping(path = {"/players" , "/players/hand={mainhand}", "/players/gender={gender}","/players/hand={mainhand}&gender={gender}"})
    public List<Player> getPlayers(@PathVariable (required=false) Optional<String> mainhand, @PathVariable(required = false) Optional<String> gender) {

        List<Player> foo = new ArrayList<>();
        if (mainhand.isPresent() && gender.isPresent()) {
            //return playerService.findBymainHandAndGender(mainhand.get(), gender.get());
        } else if (mainhand.isPresent() && gender.isEmpty()) {
            //return playerService.findByMainHand(mainhand.get());
        } else if (mainhand.isEmpty() && gender.isPresent()) {
            //return playerService.findByGender(gender.get());
        } else {
            //return (List<Player>) playerRepository.findAll();
        }
        return foo;
    }
    @PostMapping("/player")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        try {
            Player savedPlayer = this.playerService.savePlayer(player);
            return new ResponseEntity<>(savedPlayer,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/player={playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String playerId, @RequestBody Player player) {
        try {
            Player updatedPlayer = playerService.updatePlayer(UUID.fromString(playerId), player);
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/player={playerIdString}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable String playerIdString) {
        UUID playerId = UUID.fromString(playerIdString);
        rosterService.deleteRosterByPlayerId(playerId);
        playerService.deletePlayerById(playerId);
        return ResponseEntity.noContent().build();
    }
}
