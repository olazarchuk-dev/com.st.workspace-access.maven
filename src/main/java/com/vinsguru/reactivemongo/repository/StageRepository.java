package com.vinsguru.reactivemongo.repository;

import com.vinsguru.reactivemongo.entity.Stage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StageRepository extends ReactiveMongoRepository<Stage, String> {
    Flux<Stage> findAllByIdNotNullOrderByIdAsc(final Pageable page);
    Flux<Stage> findAllByUpdatedAtNotNullOrderByUpdatedAtAsc(final Pageable page);
    Flux<Stage> findAllByUpdatedAtNotNullOrderByUpdatedAtDesc(final Pageable page);
}
