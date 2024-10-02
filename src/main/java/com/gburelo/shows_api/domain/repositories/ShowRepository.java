package com.gburelo.shows_api.domain.repositories;

import com.gburelo.shows_api.domain.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
}
