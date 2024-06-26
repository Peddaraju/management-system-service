package com.app.management.system.repository;

import com.app.management.system.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SportRepository extends JpaRepository<Sport, String> {
    /**
     * Find sports with multiple players list.
     *
     * @return the list
     */
    @Query("SELECT s FROM Sport s JOIN s.players p GROUP BY s HAVING COUNT(p) >= 2")
    List<Sport> findSportsWithMultiplePlayers();

    /*
    *
    *   SELECT s.*
        FROM sports s
        JOIN player_sports ps ON s.name = ps.sport_name
        GROUP BY s.name
        HAVING COUNT(ps.email) >= 2;
    *
    * */


    /**
     * Find sports with no players list.
     *
     * @return the list
     */
    @Query("SELECT s FROM Sport s WHERE s.players IS EMPTY")
    List<Sport> findSportsWithNoPlayers();

    /*
    *
    * -- SQL equivalent
        SELECT s.*
        FROM sports s
        LEFT JOIN player_sports ps ON s.name = ps.sport_name
        WHERE ps.email IS NULL;
    *
    * */


    @Query("SELECT s FROM Sport s LEFT JOIN FETCH s.players WHERE s.name IN :names")
    Set<Sport> findByNames(@Param("names") List<String> names);

    Set<Sport> findByNameIn(Set<String> names);
}
