package com.app.management.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;


@Entity
@Table(name = "sports")
public class Sport {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "player_sports",
            joinColumns = @JoinColumn(name = "sport_name", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "email", referencedColumnName = "email")
    )
    private Set<Player> players;

    public Sport() {
    }

    public Sport(String name, Set<Player> players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }


}
