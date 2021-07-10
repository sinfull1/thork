package com.gopaychain.thork.repository;

import com.gopaychain.thork.entity.ThorkStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThorkStatusRepository extends ReactiveCrudRepository<ThorkStatus,String> {
}
