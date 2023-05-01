package com.badminton.shuttlestats.controller;

import com.badminton.shuttlestats.model.Session;
import com.badminton.shuttlestats.services.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club={clubId}/session")
public class SessionController {

    @Autowired
    private RosterService rosterService;

    @GetMapping("/")
    public ResponseEntity<List<Session>> getSessionsOfClub() {
        return new ResponseEntity<List<Session>>(HttpStatus.OK);
    }

    @PostMapping("/addSession")
    public ResponseEntity<Session> addSessionToClub() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteSession")
    public ResponseEntity<Void> deleteSession() {
        return ResponseEntity.noContent().build();
    }

}
