package com.vinsguru.reactivemongo.service;

import com.vinsguru.reactivemongo.dto.StageDTO;
import com.vinsguru.reactivemongo.repository.StageRepository;
import com.vinsguru.reactivemongo.service.mapper.StageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class StageService {

    private final StageRepository stageRepository;
    private final StageMapper stageMapper;

    public Mono<StageDTO> getStage(final String id) {
        return stageRepository.findById(id)
                .map(stageMapper::toDto);
    }

    public Flux<StageDTO> findAllByUpdatedAtNotNullOrderByUpdatedAtDesc(Pageable pageable) {
        return stageRepository.findAllByUpdatedAtNotNullOrderByUpdatedAtDesc(pageable)
                .map(stageMapper::toDto);
    }

    public Mono<StageDTO> saveStage(StageDTO dto) {
        var entity = stageMapper.toEntity(dto);
        return stageRepository.save(entity)
                .map(stageMapper::toDto);
    }

    public Mono<Void> deleteStage(final String id) {
        return stageRepository.deleteById(id);
    }

    public Mono<Long> countAllStages() {
        return stageRepository.count();
    }
}
