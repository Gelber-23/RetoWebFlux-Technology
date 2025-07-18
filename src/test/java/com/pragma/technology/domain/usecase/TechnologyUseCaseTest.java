package com.pragma.technology.domain.usecase;

import com.pragma.technology.domain.exception.InvalidTechnologyException;
import com.pragma.technology.domain.exception.TechnologyAlreadyExitsException;
import com.pragma.technology.domain.model.Technology;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TechnologyUseCaseTest {
    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    @InjectMocks
    private TechnologyUseCase technologyUseCase;

    @Test
    void getTechnologyById_ShouldReturnMono() {
        Technology tech = new Technology(1L, "Java", "Backend");
        when(technologyPersistencePort.getTechnologyById(1L)).thenReturn(Mono.just(tech));

        StepVerifier.create(technologyUseCase.getTechnologyById(1L))
                .expectNext(tech)
                .verifyComplete();

        verify(technologyPersistencePort).getTechnologyById(1L);
    }

    @Test
    void getTechnologies_ShouldReturnFlux() {
        Technology tech = new Technology(1L, "Java", "Backend");
        when(technologyPersistencePort.getTechnologies()).thenReturn(Flux.just(tech));

        StepVerifier.create(technologyUseCase.getTechnologies())
                .expectNext(tech)
                .verifyComplete();

        verify(technologyPersistencePort).getTechnologies();
    }

    @Test
    void createTechnology_ShouldCreateSuccessfully() {
        Technology tech = new Technology(1L, "Java", "Backend");
        when(technologyPersistencePort.existsByName("Java")).thenReturn(Mono.just(false));
        when(technologyPersistencePort.createTechnology(tech)).thenReturn(Mono.just(tech));

        StepVerifier.create(technologyUseCase.createTechnology(tech))
                .expectNext(tech)
                .verifyComplete();

        verify(technologyPersistencePort).existsByName("Java");
        verify(technologyPersistencePort).createTechnology(tech);
    }

    @Test
    void createTechnology_ShouldThrowIfExists() {
        Technology tech = new Technology(1L, "Java", "Backend");
        when(technologyPersistencePort.existsByName("Java")).thenReturn(Mono.just(true));

        StepVerifier.create(technologyUseCase.createTechnology(tech))
                .expectError(TechnologyAlreadyExitsException.class)
                .verify();

        verify(technologyPersistencePort).existsByName("Java");
        verify(technologyPersistencePort, never()).createTechnology(any());
    }

    @Test
    void createTechnology_ShouldThrowInvalidTechnologyException_NameBlank() {
        Technology tech = new Technology(1L, " ", "Backend");

        StepVerifier.create(technologyUseCase.createTechnology(tech))
                .expectError(InvalidTechnologyException.class)
                .verify();

        verifyNoInteractions(technologyPersistencePort);
    }

    @Test
    void createTechnology_ShouldThrowInvalidTechnologyException_DescriptionBlank() {
        Technology tech = new Technology(1L, "Java", " ");

        StepVerifier.create(technologyUseCase.createTechnology(tech))
                .expectError(InvalidTechnologyException.class)
                .verify();

        verifyNoInteractions(technologyPersistencePort);
    }

    @Test
    void createTechnology_ShouldThrowInvalidTechnologyException_NameTooLong() {
        String longName = "a".repeat(51);
        Technology tech = new Technology(1L, longName, "Backend");

        StepVerifier.create(technologyUseCase.createTechnology(tech))
                .expectError(InvalidTechnologyException.class)
                .verify();

        verifyNoInteractions(technologyPersistencePort);
    }

    @Test
    void createTechnology_ShouldThrowInvalidTechnologyException_DescriptionTooLong() {
        String longDescription = "a".repeat(101);
        Technology tech = new Technology(1L, "Java", longDescription);

        StepVerifier.create(technologyUseCase.createTechnology(tech))
                .expectError(InvalidTechnologyException.class)
                .verify();

        verifyNoInteractions(technologyPersistencePort);
    }

}