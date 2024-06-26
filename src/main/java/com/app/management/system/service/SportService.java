package com.app.management.system.service;

import com.app.management.system.entity.Sport;
import com.app.management.system.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public List<Sport> getSportsWithMultiplePlayers() {
        return sportRepository.findSportsWithMultiplePlayers();
    }

    public List<Sport> getSportsWithNoPlayers() {
        return sportRepository.findSportsWithNoPlayers();
    }

    public Set<Sport> getSportsWithPlayers(List<String> names) {
        return sportRepository.findByNames(names);
    }

    @Transactional
    public void deleteSport(String name) {
        Sport sport = sportRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Sport not found"));
        sportRepository.delete(sport);
    }
}
