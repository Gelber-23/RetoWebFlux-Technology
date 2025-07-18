package com.pragma.technology.application.mapper.response;

import com.pragma.technology.application.dto.response.TechnologyResponse;
import com.pragma.technology.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITechnologyResponseMapper {
    Technology toTechnology(TechnologyResponse response);
    TechnologyResponse toResponse(Technology  technology);
}
