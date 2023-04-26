package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public PlayerService() {}

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    public boolean checkPlayerExistsById(UUID playerId) {
        return playerRepository.existsById(playerId);
    }
    public Player savePlayer(Player playerDetails) {
        Player toSave = new Player(playerDetails.getPlayerName(),playerDetails.getPlayerGender(),playerDetails.getPlayerMainHand());
        return playerRepository.save(toSave);
    }
    public Optional<Player> getPlayerById(UUID id) {
        return playerRepository.findById(id);
    }
    public void deletePlayerById(UUID id) {
        playerRepository.deleteById(id);
    }

}
