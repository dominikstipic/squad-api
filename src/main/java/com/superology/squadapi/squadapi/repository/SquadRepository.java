package com.superology.squadapi.squadapi.repository;

import com.superology.squadapi.squadapi.models.domainSquad.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {
}
