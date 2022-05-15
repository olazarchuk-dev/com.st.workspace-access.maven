package com.vinsguru.reactivemongo.service.mapper;

import com.vinsguru.reactivemongo.dto.StageDTO;
import com.vinsguru.reactivemongo.entity.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StageMapper {

    @Mapping(target = "date", expression = "java( entity.getDate()!=null ? java.time.Instant.ofEpochMilli(entity.getDate()) : null )")
    @Mapping(target = "createdAt", expression = "java( entity.getCreatedAt()!=null ? java.time.Instant.ofEpochMilli(entity.getCreatedAt()) : null )")
    @Mapping(target = "updatedAt", expression = "java( entity.getUpdatedAt()!=null ? java.time.Instant.ofEpochMilli(entity.getUpdatedAt()) : null )")
    StageDTO toDto(Stage entity);

    @Mapping(target = "date", expression = "java( dto.getDate()!=null ? dto.getDate().toEpochMilli() : null )")
    @Mapping(target = "createdAt", expression = "java( dto.getCreatedAt()!=null ? dto.getCreatedAt().toEpochMilli() : null )")
    @Mapping(target = "updatedAt", expression = "java( dto.getUpdatedAt()!=null ? dto.getUpdatedAt().toEpochMilli() : null )")
    Stage toEntity(StageDTO dto);

}
