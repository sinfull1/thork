package com.gopaychain.matching.repository;

import com.gopaychain.matching.entity.ticket.Ticket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends ReactiveCrudRepository<Ticket, Long> {

}
