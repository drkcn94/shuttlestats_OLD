package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Club;
import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.Roster;
import com.badminton.shuttlestats.repositories.ClubRepository;
import com.badminton.shuttlestats.repositories.PlayerRepository;
import com.badminton.shuttlestats.repositories.RosterRepository;
import com.badminton.shuttlestats.services.ClubService;
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
@RequestMapping("/clubs")
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private RosterService rosterService;
    @Autowired
    private PlayerService playerService;
    public ClubController() {}

    @GetMapping("/")
    public ResponseEntity<List<Club>> getClubs() {
        return new ResponseEntity<>(clubService.getAllClubs(),HttpStatus.OK);
    }

    @GetMapping("/{clubId}")
    @ResponseBody
    public ResponseEntity<Club> getClub(@PathVariable UUID clubId) {
        Optional<Club> club = clubService.getClubById(clubId);

        if(club.isPresent()) {
            return new ResponseEntity<>(club.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{clubId}/players")
    public List<Player> getAllPlayersOfClub(@PathVariable UUID clubId) {
        List<Roster> rosterIds = rosterService.findPlayersByClubId(clubId);

        List<UUID> playerIdsOfClub = new ArrayList<>();

        for (Roster playerId : rosterIds) {
            playerIdsOfClub.add(playerId.getPlayerId());
        }

        List<Player> playersOfClub = playerService.findAllPlayersOfClub(playerIdsOfClub);



        return playersOfClub;
    }

    @GetMapping("/{clubId}/member/{playerId}")
    public ResponseEntity<Player> getPlayerOfClub(@PathVariable UUID clubId, UUID playerId) {
        Optional<Roster> playerToFind = rosterService.findPlayerByClubIdAndPlayerId(clubId,playerId);

        if (playerToFind.isPresent()) {
            Optional<Player> player = playerService.getPlayerById(playerToFind.get().getPlayerId());

            if (player.isPresent()) {
                return new ResponseEntity<>(player.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/club")
    public ResponseEntity<Club> addClub(@RequestBody Club club) {
        Club savedClub = clubService.saveClub(club);
        return new ResponseEntity<>(savedClub, HttpStatus.OK);
    }

    @DeleteMapping("/club/{clubIdString}")
    public ResponseEntity<Void> deleteClubById(@PathVariable String clubIdString) {
        UUID clubId = UUID.fromString(clubIdString);
        clubService.deleteClubById(clubId);
        return ResponseEntity.noContent().build();
    }
}
