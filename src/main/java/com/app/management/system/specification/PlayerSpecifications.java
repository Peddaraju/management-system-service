package com.app.management.system.specification;

import com.app.management.system.entity.Player;
import com.app.management.system.entity.Sport;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecifications {
    public static Specification<Player> hasSport(String sportName) {
        return (root, query, criteriaBuilder) -> {
            if (sportName == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true, no filtering
            }
            Join<Player, Sport> sportsJoin = root.join("sports", JoinType.INNER);
            return criteriaBuilder.equal(sportsJoin.get("name"), sportName);
        };
    }

}
