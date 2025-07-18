package com.pragma.technology.domain.usecase;

import com.pragma.technology.domain.api.ITechnologyServicePort;
import com.pragma.technology.domain.exception.InvalidTechnologyException;
import com.pragma.technology.domain.exception.TechnologyAlreadyExitsException;
import com.pragma.technology.domain.model.Technology;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.technology.domain.util.ExceptionConstans;
import com.pragma.technology.domain.util.ValueConstants;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }


    @Override
    public Mono<Technology> getTechnologyById(Long id) {
        return technologyPersistencePort.getTechnologyById(id);
    }

    @Override
    public Flux<Technology> getTechnologies() {
        return technologyPersistencePort.getTechnologies();
    }

    @Override
    public  Mono<Technology> createTechnology(Technology technology) {

        return isValidTechnology(technology)
                .flatMap( validTechnology -> technologyPersistencePort.existsByName(validTechnology.getName()))
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error( new TechnologyAlreadyExitsException(ExceptionConstans.TECHNOLOGY_ALREADY_EXIST)))
                .flatMap(exists -> technologyPersistencePort.createTechnology(technology));
    }

    private Mono<Technology> isValidTechnology (Technology technology){
        List<String> errors = new ArrayList<>();

        if (technology.getName() == null || technology.getName().isBlank()) {
            errors.add(ExceptionConstans.TECHNOLOGY_NAME_REQUIRED);
        }
        if (technology.getName() != null && technology.getName().length() > ValueConstants.MAX_LENGTH_NAME_TECHNOLOGY) {
            errors.add(ExceptionConstans.TECHNOLOGY_NAME_EXCEEDS_LIMIT);
        }
        if (technology.getDescription() == null || technology.getDescription().isBlank()) {
            errors.add(ExceptionConstans.TECHNOLOGY_DESCRIPTION_REQUIRED);
        }
        if (technology.getDescription() != null && technology.getDescription().length() > ValueConstants.MAX_LENGTH_DESCRIPTION_TECHNOLOGY) {
            errors.add(ExceptionConstans.TECHNOLOGY_DESCRIPTION_EXCEEDS_LIMIT);
        }

        if (!errors.isEmpty()) {
            return Mono.error(new InvalidTechnologyException(String.join("; ", errors)));
        }
        return  Mono.just(technology) ;
    }
}
