package com.pragma.technology.application.handler;

import com.pragma.technology.application.dto.request.TechnologyRequest;
import com.pragma.technology.application.dto.response.TechnologyResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITechnologyHandler {

    Mono<TechnologyResponse> getTechnologyById(Long id);
    Flux<TechnologyResponse> getTechnologies();
    Mono<TechnologyResponse> createTechnology (TechnologyRequest technologyRequest);
}
