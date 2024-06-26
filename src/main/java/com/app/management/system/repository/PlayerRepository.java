package com.app.management.system.repository;

import com.app.management.system.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String>, JpaSpecificationExecutor<Player> {
    /**
     * Find by gender and level and age list.
     *
     * @param gender the gender
     * @param level  the level
     * @param age    the age
     * @return the list
     */
    List<Player> findByGenderAndLevelAndAge(String gender, int level, int age);

    /*  SQL equivalent
        SELECT * FROM players WHERE gender = ? AND level = ? AND age = ?;
    */


    @Query("SELECT p FROM Player p LEFT JOIN p.sports s WHERE s IS NULL")
    List<Player> findPlayersWithNoSports();
}
