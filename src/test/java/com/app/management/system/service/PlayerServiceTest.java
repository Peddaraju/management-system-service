package com.app.management.system.service;

import com.app.management.system.entity.Player;
import com.app.management.system.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testFindPlayersByGenderLevelAndAge() {

        Player player1 = new Player();
        player1.setEmail("player1@example.com");
        player1.setGender("male");
        player1.setLevel(5);
        player1.setAge(30);

        Player player2 = new Player();
        player2.setEmail("player2@example.com");
        player2.setGender("male");
        player2.setLevel(5);
        player2.setAge(30);

        List<Player> expectedPlayers = Arrays.asList(player1, player2);

        when(playerRepository.findByGenderAndLevelAndAge("male", 5, 30)).thenReturn(expectedPlayers);

        List<Player> actualPlayers = playerService.findPlayersByGenderLevelAndAge("male", 5, 30);

        assertEquals(expectedPlayers.size(), actualPlayers.size(), "The number of players retrieved should match expected");
        assertIterableEquals(expectedPlayers, actualPlayers, "The retrieved players should exactly match the expected list");

        verify(playerRepository, times(1)).findByGenderAndLevelAndAge("male", 5, 30);
    }

    @Test
    public void testGetPlayersWithNoSports() {

        Player player1 = new Player();
        Player player2 = new Player();
        List<Player> expectedPlayers = Arrays.asList(player1, player2);

        when(playerRepository.findPlayersWithNoSports()).thenReturn(expectedPlayers);

        List<Player> actualPlayers = playerService.getPlayersWithNoSports();

        assertNotNull(actualPlayers, "List of players should not be null");
        assertEquals(expectedPlayers.size(), actualPlayers.size(), "The size of players lists should match");
        assertTrue(actualPlayers.containsAll(expectedPlayers), "The actual list should contain all the expected players");

        // Verify repository interaction
        verify(playerRepository, times(1)).findPlayersWithNoSports();
    }


}
