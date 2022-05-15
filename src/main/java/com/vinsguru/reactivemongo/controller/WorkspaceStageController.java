package com.vinsguru.reactivemongo.controller;

import com.vinsguru.reactivemongo.dto.WorkspaceStageDTO;
import com.vinsguru.reactivemongo.service.WorkspaceStageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkspaceStageController {
    private final WorkspaceStageService workspaceStageService;

    @GetMapping("/workspace/stage-one/{id}")
    public Mono<WorkspaceStageDTO> getStageById(@PathVariable String id) {
        return workspaceStageService.getStage(id)
                .delayElement(Duration.ofMillis(5));
    }

    @GetMapping("/workspace/stage-all")
    public Flux<WorkspaceStageDTO> getAllStages(final @RequestParam(name = "page") int page,
                                                final @RequestParam(name = "size") int size) {
        return this.workspaceStageService.findAll(PageRequest.of(page, size))
                .delayElements(Duration.ofMillis(5));
    }

//    @GetMapping("/workspace/stage-all")
//    public Flux<WorkspaceStageDTO> getAllStages(final @RequestParam(name = "page") int page,
//                                                final @RequestParam(name = "size") int size,
//                                                final @RequestParam(name = "sort") String sort) {
//        return this.workspaceStageService.findAll(PageRequest.of(page, size, Sort.by(sort).descending()))
//                .delayElements(Duration.ofMillis(5));
//    }

    @GetMapping("/workspace/stage-count")
    public Mono<Long> count() {
        return this.workspaceStageService.count()
                .delayElement(Duration.ofMillis(5));
    }
}
