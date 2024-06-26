package com.app.management.system.controller;

import com.app.management.system.entity.Player;
import com.app.management.system.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @Test
    public void testGetPlayersWithNoSports() throws Exception {
        mockMvc = standaloneSetup(playerController).build();

        List<Player> players = Collections.emptyList();
        when(playerService.getPlayersWithNoSports()).thenReturn(players);

        mockMvc.perform(get("/api/players/no-sports"))
                .andExpect(status().isNoContent());

        verify(playerService, times(1)).getPlayersWithNoSports();
    }
}
