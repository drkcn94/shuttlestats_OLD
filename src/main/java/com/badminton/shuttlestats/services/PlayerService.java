package com.badminton.shuttlestats.services;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.model.enums.Gender;
import com.badminton.shuttlestats.model.enums.MainHand;
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

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> findAllPlayersOfClub(List<UUID> playerIds) {
        return playerRepository.findAllByplayerIdIn(playerIds);
    }
    public boolean checkPlayerExistsById(UUID playerId) {
        return playerRepository.existsById(playerId);
    }
    public Player savePlayer(Player playerDetails) {

        validatePlayer(playerDetails);

        Player toSave = new Player(playerDetails.getPlayerName(),playerDetails.getPlayerGender(),playerDetails.getPlayerMainHand());
        return playerRepository.save(toSave);
    }

    public Player updatePlayer(UUID playerId, Player player) {
        if (player == null) {
            throw new IllegalArgumentException();
        }

        validatePlayer(player);

        Player playerToFind = playerRepository.getReferenceById(playerId);
        if(playerToFind == null) {
            throw new IllegalArgumentException();
        }

        if (playerToFind.getPlayerId() != playerId) {
            throw new IllegalArgumentException();
        }

        return playerRepository.save(player);
    }
    public Optional<Player> getPlayerById(UUID id) {
        return playerRepository.findById(id);
    }
    public void deletePlayerById(UUID id) {
        playerRepository.deleteById(id);
    }

    public void validatePlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException();
        }

        if(player.getPlayerName().matches(".*[=()/\\\\{}\\[\\]&*:!?].*")) {
            throw new IllegalArgumentException();
        }

        if(!(player.getPlayerGender().equals(Gender.MALE.toString())) && !(player.getPlayerGender().equals(Gender.FEMALE.toString()))) {
            throw new IllegalArgumentException();
        }

        if(!(player.getPlayerMainHand().equals(MainHand.RIGHT.toString())) && !(player.getPlayerMainHand().equals(MainHand.LEFT.toString()))) {
            throw new IllegalArgumentException();
        }
    }

}
