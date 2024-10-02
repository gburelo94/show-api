package com.gburelo.shows_api.domain.repositories;

import com.gburelo.shows_api.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    List<Ticket> findByShowIdAndStatus(Integer showId, String status);
}
