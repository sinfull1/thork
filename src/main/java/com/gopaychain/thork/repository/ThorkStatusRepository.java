package com.gopaychain.thork.repository;

import com.gopaychain.thork.model.ThorkStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ThorkStatusRepository extends ReactiveCrudRepository<ThorkStatus,String> {
}
