package com.app.management.system.controller;

import com.app.management.system.entity.Player;
import com.app.management.system.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/no-sports")
    public ResponseEntity<List<Player>> getPlayersWithNoSports() {
        List<Player> players = playerService.getPlayersWithNoSports();
        if (players.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(players);
    }

    @PutMapping("/{email}/sports")
    public ResponseEntity<Player> updatePlayerSports(@PathVariable String email, @RequestBody Set<String> sportNames) {
        try {
            Player updatedPlayer = playerService.updatePlayerSports(email, sportNames);
            return ResponseEntity.ok(updatedPlayer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Player>> getPlayers(
            @RequestParam Optional<String> sportName,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Player> players = playerService.findPlayers(sportName, pageable);
        return ResponseEntity.ok(players);
    }




}
