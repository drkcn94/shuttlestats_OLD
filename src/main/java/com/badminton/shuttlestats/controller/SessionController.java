package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Session;
import com.badminton.shuttlestats.services.ClubMemberService;
import com.badminton.shuttlestats.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/club={clubId}/session")
public class SessionController {

    @Autowired
    private ClubMemberService clubMemberService;

    @Autowired
    private SessionService sessionService;

    public SessionController () {}

    @GetMapping("/")
    public ResponseEntity<List<Session>> getAllSessions() {
        return new ResponseEntity<>(sessionService.getAllSessions(),HttpStatus.OK);
    }

    @PostMapping("/addSession")
    public ResponseEntity<Session> addSessionToClub(@PathVariable String clubUUID, @RequestBody Session session) {
        try {
            Session savedSession = sessionService.saveSession(UUID.fromString(clubUUID), session);
            return new ResponseEntity<>(savedSession, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteSession")
    public ResponseEntity<Void> deleteSession() {
        return ResponseEntity.noContent().build();
    }

}
