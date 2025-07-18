package com.pragma.technology.infraestructure.output.jpa.mapper;

import com.pragma.technology.domain.model.Technology;
import com.pragma.technology.infraestructure.output.jpa.entity.TechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITechnologyEntityMapper {
    Technology toModel (TechnologyEntity technologyEntity);
    TechnologyEntity toEntity ( Technology technology);

}
