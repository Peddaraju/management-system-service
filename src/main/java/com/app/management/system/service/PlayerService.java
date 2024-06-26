package com.app.management.system.service;

import com.app.management.system.entity.Player;
import com.app.management.system.entity.Sport;
import com.app.management.system.repository.PlayerRepository;
import com.app.management.system.repository.SportRepository;
import com.app.management.system.specification.PlayerSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SportRepository sportRepository;

    public List<Player> findPlayersByGenderLevelAndAge(String gender, int level, int age) {
        return playerRepository.findByGenderAndLevelAndAge(gender, level, age);
    }

    public List<Player> getPlayersWithNoSports() {
        return playerRepository.findPlayersWithNoSports();
    }

    @Transactional
    public Player updatePlayerSports(String email, Set<String> sportNames) {
        Player player = playerRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Set<Sport> newSports = sportRepository.findByNameIn(sportNames);
        player.setSports(newSports);

        return playerRepository.save(player);
    }

    public Page<Player> findPlayers(Optional<String> sportName, Pageable pageable) {
        return playerRepository.findAll(PlayerSpecifications.hasSport(sportName.orElse(null)), pageable);
    }
}
