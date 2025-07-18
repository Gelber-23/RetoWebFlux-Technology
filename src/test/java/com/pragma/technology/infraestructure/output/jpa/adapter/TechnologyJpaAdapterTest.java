package com.pragma.technology.infraestructure.output.jpa.adapter;


import com.pragma.technology.domain.model.Technology;
import com.pragma.technology.infraestructure.output.jpa.entity.TechnologyEntity;
import com.pragma.technology.infraestructure.output.jpa.mapper.ITechnologyEntityMapper;
import com.pragma.technology.infraestructure.output.jpa.repository.ITechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
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
class TechnologyJpaAdapterTest {

    @Mock
    private ITechnologyRepository technologyRepository;

    @Mock
    private ITechnologyEntityMapper technologyEntityMapper;

    @InjectMocks
    private TechnologyJpaAdapter technologyJpaAdapter;

    private Technology technology;
    private TechnologyEntity technologyEntity;

    @BeforeEach
    void setUp() {
        technology = new Technology(1L, "Java", "Backend");
        technologyEntity = new TechnologyEntity(1L, "Java", "Backend");
    }

    @Test
    void getTechnologyById_ShouldReturnTechnology() {
        when(technologyRepository.findById(1L)).thenReturn(Mono.just(technologyEntity));
        when(technologyEntityMapper.toModel(technologyEntity)).thenReturn(technology);

        StepVerifier.create(technologyJpaAdapter.getTechnologyById(1L))
                .expectNext(technology)
                .verifyComplete();

        verify(technologyRepository).findById(1L);
        verify(technologyEntityMapper).toModel(technologyEntity);
    }

    @Test
    void getTechnologies_ShouldReturnFluxOfTechnologies() {
        when(technologyRepository.findAll()).thenReturn(Flux.just(technologyEntity));
        when(technologyEntityMapper.toModel(technologyEntity)).thenReturn(technology);

        StepVerifier.create(technologyJpaAdapter.getTechnologies())
                .expectNext(technology)
                .verifyComplete();

        verify(technologyRepository).findAll();
        verify(technologyEntityMapper).toModel(technologyEntity);
    }

    @Test
    void existsByName_ShouldReturnBoolean() {
        when(technologyRepository.existsByName("Java")).thenReturn(Mono.just(true));

        StepVerifier.create(technologyJpaAdapter.existsByName("Java"))
                .expectNext(true)
                .verifyComplete();

        verify(technologyRepository).existsByName("Java");
    }

    @Test
    void createTechnology_ShouldReturnCreatedTechnology() {
        when(technologyEntityMapper.toEntity(technology)).thenReturn(technologyEntity);
        when(technologyRepository.save(technologyEntity)).thenReturn(Mono.just(technologyEntity));
        when(technologyEntityMapper.toModel(technologyEntity)).thenReturn(technology);

        StepVerifier.create(technologyJpaAdapter.createTechnology(technology))
                .expectNext(technology)
                .verifyComplete();

        verify(technologyEntityMapper).toEntity(technology);
        verify(technologyRepository).save(technologyEntity);
        verify(technologyEntityMapper).toModel(technologyEntity);
    }
}