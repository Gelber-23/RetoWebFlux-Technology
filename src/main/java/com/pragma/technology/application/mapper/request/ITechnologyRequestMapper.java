package com.pragma.technology.application.mapper.request;

import com.pragma.technology.application.dto.request.TechnologyRequest;
import com.pragma.technology.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITechnologyRequestMapper {
    Technology toTechnology(TechnologyRequest technologyRequest);
}
