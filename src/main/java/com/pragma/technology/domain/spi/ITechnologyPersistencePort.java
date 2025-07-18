package com.pragma.technology.domain.spi;

import com.pragma.technology.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITechnologyPersistencePort {

    Mono<Technology> getTechnologyById(Long id);
    Flux<Technology> getTechnologies();
    Mono<Boolean> existsByName(String name);
    Mono<Technology> createTechnology (Technology technology);
}
