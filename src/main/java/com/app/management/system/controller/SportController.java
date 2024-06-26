package com.app.management.system.controller;

import com.app.management.system.entity.Sport;
import com.app.management.system.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    @Autowired
    private SportService sportService;

    @GetMapping
    public ResponseEntity<?> getSportsWithPlayers(@RequestParam List<String> names) {
        if (names.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: No sport names provided.");
        }

        Set<Sport> sports = sportService.getSportsWithPlayers(names);
        if (sports.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sports found with the given names.");
        }
        return ResponseEntity.ok(sports);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteSport(@PathVariable String name) {
        try {
            sportService.deleteSport(name);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
