package com.superology.squadapi.squadapi.repository;

import com.superology.squadapi.squadapi.models.domainPlayer.DomainPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<DomainPlayer, Long> {
}
