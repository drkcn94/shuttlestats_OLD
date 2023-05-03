package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Club;
import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.ClubMember;
import com.badminton.shuttlestats.services.ClubService;
import com.badminton.shuttlestats.services.PlayerService;
import com.badminton.shuttlestats.services.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private ClubMemberService clubMemberService;
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
        List<ClubMember> rosterIds = clubMemberService.findPlayersByClubId(clubId);

        List<UUID> playerIdsOfClub = new ArrayList<>();

        for (ClubMember playerId : rosterIds) {
            playerIdsOfClub.add(playerId.getPlayerId());
        }

        List<Player> playersOfClub = playerService.findAllPlayersOfClub(playerIdsOfClub);



        return playersOfClub;
    }

    @GetMapping("/{clubId}/member/{playerId}")
    public ResponseEntity<Player> getPlayerOfClub(@PathVariable UUID clubId, UUID playerId) {
        Optional<ClubMember> playerToFind = clubMemberService.findPlayerByClubIdAndPlayerId(clubId,playerId);

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

    @PostMapping("/createClub")
    public ResponseEntity<Club> addClub(@RequestBody Club club) {
        Club savedClub = clubService.saveClub(club);
        return new ResponseEntity<>(savedClub, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClub={clubIdString}")
    public ResponseEntity<Void> deleteClubById(@PathVariable String clubIdString) {
        UUID clubId = UUID.fromString(clubIdString);
        clubService.deleteClubById(clubId);
        return ResponseEntity.noContent().build();
    }
}
