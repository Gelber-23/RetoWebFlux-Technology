package com.pragma.technology.domain.api;

import com.pragma.technology.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITechnologyServicePort {

    Mono<Technology> getTechnologyById(Long id);
    Flux<Technology> getTechnologies();
    Mono<Technology> createTechnology (Technology technology);
    Mono<Void> deleteTechnologyById(Long id);

}
