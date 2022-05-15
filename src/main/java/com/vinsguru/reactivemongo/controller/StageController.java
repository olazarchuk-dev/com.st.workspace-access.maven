package com.vinsguru.reactivemongo.controller;

import com.vinsguru.reactivemongo.dto.StageDTO;
import com.vinsguru.reactivemongo.service.StageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class StageController {

    private final StageService stageService;

    @GetMapping("admin/stages/{id}")
    public Mono<StageDTO> get(@PathVariable String id) {
        log.debug("Request to get stage by id: {}", id);
        return stageService.getStage(id)
                .delayElement(Duration.ofMillis(5));
    }

    @GetMapping("admin/stages")
    public Flux<StageDTO> getAll(final @RequestParam(name = "page") int page,
                                 final @RequestParam(name = "size") int size) {
        log.debug("Request to stages by page={}, size={}", page, size);
        return stageService.findAllByUpdatedAtNotNullOrderByUpdatedAtDesc(PageRequest.of(page, size))
                .delayElements(Duration.ofMillis(5));
    }

    @PutMapping("admin/stages")
    public Mono<StageDTO> edit(@RequestBody StageDTO dto) {
        log.debug("Request to edit old stage: {}", dto);
        return stageService.saveStage(dto)
                .delayElement(Duration.ofMillis(5));
    }

    @DeleteMapping("admin/stages/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        log.debug("Request to delete stage by id: {}", id);
        return stageService.deleteStage(id)
                .delayElement(Duration.ofMillis(5));
    }

    @GetMapping("admin/stages-count")
    public Mono<Long> count() {
        log.debug("Request to count stages");
        return this.stageService.countAllStages()
                .delayElement(Duration.ofMillis(5));
    }
}
