package com.vinsguru.reactivemongo.repository;

import com.vinsguru.reactivemongo.entity.WorkspaceStage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WorkspaceStageRepository extends ReactiveMongoRepository<WorkspaceStage, String> {
    Flux<WorkspaceStage> findAllByIdNotNullOrderByIdAsc(final Pageable page);
    Flux<WorkspaceStage> findAllByUpdatedAtNotNullOrderByUpdatedAtAsc(final Pageable page);
    Flux<WorkspaceStage> findAllByUpdatedAtNotNullOrderByUpdatedAtDesc(final Pageable page);
}
