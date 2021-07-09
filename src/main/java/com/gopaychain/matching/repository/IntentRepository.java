package com.gopaychain.matching.repository;

import com.gopaychain.matching.entity.ticket.Intent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepository extends ReactiveCrudRepository<Intent, Long> {
}
