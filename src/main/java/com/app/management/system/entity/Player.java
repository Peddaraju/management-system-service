package com.app.management.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;


@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender")
    private String gender;

    @ManyToMany(mappedBy = "players")
    private Set<Sport> sports;

    public Player() {
    }

    public Player(String email, int level, int age, String gender, Set<Sport> sports) {
        this.email = email;
        this.level = level;
        this.age = age;
        this.gender = gender;
        this.sports = sports;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }
}
