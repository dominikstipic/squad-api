package com.superology.squadapi.squadapi.repository;

import com.superology.squadapi.squadapi.models.domainSquad.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadPlayerRepository extends JpaRepository<Player, Long> {
}
