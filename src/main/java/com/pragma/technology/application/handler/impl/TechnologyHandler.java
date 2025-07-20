package com.pragma.technology.application.handler.impl;

import com.pragma.technology.application.dto.request.TechnologyRequest;
import com.pragma.technology.application.dto.response.TechnologyResponse;
import com.pragma.technology.application.handler.ITechnologyHandler;
import com.pragma.technology.application.mapper.request.ITechnologyRequestMapper;
import com.pragma.technology.application.mapper.response.ITechnologyResponseMapper;
import com.pragma.technology.domain.api.ITechnologyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TechnologyHandler implements ITechnologyHandler {

    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;


    @Override
    public Mono<TechnologyResponse> getTechnologyById(Long id) {

        return technologyServicePort.getTechnologyById(id)
                .map(technologyResponseMapper::toResponse);
    }

    @Override
    public Flux<TechnologyResponse> getTechnologies() {
        return technologyServicePort.getTechnologies()
                .map(technologyResponseMapper::toResponse);
    }

    @Override
    public Mono<TechnologyResponse> createTechnology(TechnologyRequest technologyRequest) {
        return Mono.just(technologyRequest)
                .map(technologyRequestMapper::toTechnology)
                .flatMap(technologyServicePort::createTechnology)
                .map(technologyResponseMapper::toResponse);

    }

    @Override
    public Mono<Void> deleteTechnologyById(Long id) {
        return technologyServicePort.deleteTechnologyById(id);
    }
}
