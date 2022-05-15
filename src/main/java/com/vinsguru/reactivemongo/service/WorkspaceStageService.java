package com.vinsguru.reactivemongo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinsguru.reactivemongo.dto.ChartObj;
import com.vinsguru.reactivemongo.dto.WorkspaceStageDTO;
import com.vinsguru.reactivemongo.repository.WorkspaceStageRepository;
import com.vinsguru.reactivemongo.service.mapper.WorkspaceStageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.json.JSONException;
import org.json.JSONObject;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkspaceStageService {
    private final WorkspaceStageRepository workspaceStageRepository;
    private final WorkspaceStageMapper workspaceStageMapper;
    private final ObjectMapper objectMapper;

    public Mono<WorkspaceStageDTO> getStage(final String id) {
        return workspaceStageRepository.findById(id)
                .map(workspaceStageMapper::toDto)
                .map(obj -> {
                    var payloadDecode = obj.getPayloadDecode();
                    payloadDecode = payloadDecode.substring(40, payloadDecode.length()-1);
                    try {
                        var chart = objectMapper.readValue(payloadDecode, ChartObj.Chart.class);
                        var payloadChart = objectMapper.writeValueAsString(chart);
                        log.info("{}", payloadChart);
                        obj.setPayloadDecode(payloadChart);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return obj;
                });
    }

    public Flux<WorkspaceStageDTO> findAll(Pageable pageable) {
        return workspaceStageRepository.findAllByUpdatedAtNotNullOrderByUpdatedAtDesc(pageable)
                .map(workspaceStageMapper::toDto)
                .map(obj -> {
                    var payloadDecode = obj.getPayloadDecode();
                    payloadDecode = payloadDecode.substring(40, payloadDecode.length()-1);
//                    log.info("{}", payloadDecode);

                    try {
//                        var object = objectMapper.readValue(payloadDecode, Object.class);
//                        log.info("{}", object);
//
//                        JSONObject jObject = new JSONObject(payloadDecode);
//                        log.info("{}", jObject);
//                        log.info("{}", jObject.length());
//                        log.info("\n{}", jObject.toString(2));

                        var chart = objectMapper.readValue(payloadDecode, ChartObj.Chart.class);
//                        log.info("{}", chart);
                        var payloadChart = objectMapper.writeValueAsString(chart);
                        log.info("{}", payloadChart);
                        obj.setPayloadDecode(payloadChart);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
                    }
                    return obj;
                });
    }

    public Mono<Long> count() {
        return workspaceStageRepository.count();
    }
}
