package com.app.management.system.service;

import com.app.management.system.entity.Sport;
import com.app.management.system.repository.SportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SportServiceTest {

    @Mock
    private SportRepository sportRepository;

    @InjectMocks
    private SportService sportService;

    @Test
    public void testGetSportsWithMultiplePlayers() {

        Sport sport1 = new Sport();
        sport1.setName("Soccer");
        Sport sport2 = new Sport();
        sport2.setName("Basketball");
        List<Sport> expectedSports = Arrays.asList(sport1, sport2);

        when(sportRepository.findSportsWithMultiplePlayers()).thenReturn(expectedSports);

        List<Sport> actualSports = sportService.getSportsWithMultiplePlayers();

        assertNotEquals(0, actualSports.size(), "Sports list should not be empty");
        assertEquals(expectedSports.size(), actualSports.size(), "The number of sports should match");
        assertTrue(actualSports.containsAll(expectedSports), "The returned sports should match the expected ones");

        verify(sportRepository, times(1)).findSportsWithMultiplePlayers();
    }

    @Test
    public void testGetSportsWithNoPlayers() {

        List<Sport> expectedSports = new ArrayList<>();  // Assuming no sports with players
        when(sportRepository.findSportsWithNoPlayers()).thenReturn(expectedSports);

        List<Sport> actualSports = sportService.getSportsWithNoPlayers();

        assertTrue(actualSports.isEmpty(), "The list of sports should be empty as there are no players associated with any sport");

        verify(sportRepository, times(1)).findSportsWithNoPlayers();
    }
}
