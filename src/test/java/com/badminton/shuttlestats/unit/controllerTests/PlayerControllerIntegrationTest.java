package com.badminton.shuttlestats.unit.controllerTests;

import com.badminton.shuttlestats.model.Player;
import com.badminton.shuttlestats.repositories.PlayerRepository;
import com.badminton.shuttlestats.services.PlayerService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public class PlayerControllerIntegrationTest {

    private PlayerRepository playerRepository;
    private PlayerService playerService;

    @BeforeEach
    void setupService() {
        playerRepository = mock(PlayerRepository.class);
        playerService = new PlayerService(playerRepository);
    }


    @Test
    public void createPlayerTest() {

        Player player = new Player("Derek", "MALE", "RIGHT");
        player.setPlayerId(null);

        doNothing().when(playerService).validatePlayer(player);
        when(playerRepository.save(player)).thenReturn(new Player("Derek", "MALE", "RIGHT"));

        Player toAssert = playerService.savePlayer(player);


    }
}
