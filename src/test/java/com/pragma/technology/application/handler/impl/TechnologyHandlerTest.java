package com.pragma.technology.application.handler.impl;

import com.pragma.technology.application.dto.request.TechnologyRequest;
import com.pragma.technology.application.dto.response.TechnologyResponse;
import com.pragma.technology.application.mapper.request.ITechnologyRequestMapper;
import com.pragma.technology.application.mapper.response.ITechnologyResponseMapper;
import com.pragma.technology.domain.api.ITechnologyServicePort;
import com.pragma.technology.domain.model.Technology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnologyHandlerTest {

    @Mock
    private ITechnologyServicePort technologyServicePort;

    @Mock
    private ITechnologyRequestMapper technologyRequestMapper;

    @Mock
    private ITechnologyResponseMapper technologyResponseMapper;

    @InjectMocks
    private TechnologyHandler technologyHandler;

    @Test
    void getTechnologyById_ShouldReturnResponse() {
        Technology tech = new Technology(1L, "Java", "Backend");
        TechnologyResponse response = new TechnologyResponse(1L, "Java", "Backend");

        when(technologyServicePort.getTechnologyById(1L)).thenReturn(Mono.just(tech));
        when(technologyResponseMapper.toResponse(tech)).thenReturn(response);

        StepVerifier.create(technologyHandler.getTechnologyById(1L))
                .expectNext(response)
                .verifyComplete();

        verify(technologyServicePort).getTechnologyById(1L);
        verify(technologyResponseMapper).toResponse(tech);
    }

    @Test
    void getTechnologies_ShouldReturnListOfResponses() {
        Technology tech = new Technology(1L, "Java", "Backend");
        TechnologyResponse response = new TechnologyResponse(1L, "Java", "Backend");

        when(technologyServicePort.getTechnologies()).thenReturn(Flux.just(tech));
        when(technologyResponseMapper.toResponse(tech)).thenReturn(response);

        StepVerifier.create(technologyHandler.getTechnologies())
                .expectNext(response)
                .verifyComplete();

        verify(technologyServicePort).getTechnologies();
        verify(technologyResponseMapper).toResponse(tech);
    }

    @Test
    void createTechnology_ShouldReturnCreatedResponse() {
        TechnologyRequest request = new TechnologyRequest("Java", "Backend");
        Technology tech = new Technology(1L, "Java", "Backend");
        TechnologyResponse response = new TechnologyResponse(1L, "Java", "Backend");

        when(technologyRequestMapper.toTechnology(request)).thenReturn(tech);
        when(technologyServicePort.createTechnology(tech)).thenReturn(Mono.just(tech));
        when(technologyResponseMapper.toResponse(tech)).thenReturn(response);

        StepVerifier.create(technologyHandler.createTechnology(request))
                .expectNext(response)
                .verifyComplete();

        verify(technologyRequestMapper).toTechnology(request);
        verify(technologyServicePort).createTechnology(tech);
        verify(technologyResponseMapper).toResponse(tech);
    }
}