package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.ClubMember;
import com.badminton.shuttlestats.services.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club={clubId}/member")
public class ClubMemberController {

    @Autowired
    private ClubMemberService clubMemberService;

    @GetMapping("/")
    public ResponseEntity<List<Player>> getPlayersOfClub() {
        return new ResponseEntity<List<Player>>(HttpStatus.OK);
    }

    @PostMapping("/addMember={playerId}")
    public ResponseEntity<ClubMember> addPlayerToClubRoster(@PathVariable String clubId, @PathVariable String playerId) {
        clubMemberService.savePlayerToRoster(clubId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllMembersOfClub")
    public ResponseEntity<Void> deleteAllMembersOfClub(@PathVariable String clubId){
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteMemberOfClub")
    public ResponseEntity<Void> deleteMemberFromClub(@PathVariable String clubId, Player player) {
        return ResponseEntity.noContent().build();
    }

}
