package com.vinsguru.reactivemongo.service.mapper;

import com.vinsguru.reactivemongo.dto.WorkspaceStageDTO;
import com.vinsguru.reactivemongo.entity.WorkspaceStage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkspaceStageMapper {

    @Mapping(target = "date", expression = "java( entity.getDate()!=null ? java.time.Instant.ofEpochMilli(entity.getDate()) : null )")
    @Mapping(target = "createdAt", expression = "java( entity.getCreatedAt()!=null ? java.time.Instant.ofEpochMilli(entity.getCreatedAt()) : null )")
    @Mapping(target = "updatedAt", expression = "java( entity.getUpdatedAt()!=null ? java.time.Instant.ofEpochMilli(entity.getUpdatedAt()) : null )")
    @Mapping(target = "payloadDecode", expression = "java( com.vinsguru.reactivemongo.utils.GzipUtil.doHandleDecompressPayload(entity.getPayload()) )")
    WorkspaceStageDTO toDto(WorkspaceStage entity);

}
