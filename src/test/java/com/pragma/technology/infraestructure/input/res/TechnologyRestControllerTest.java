package com.pragma.technology.infraestructure.input.res;

import com.pragma.technology.application.dto.request.TechnologyRequest;
import com.pragma.technology.application.dto.response.TechnologyResponse;
import com.pragma.technology.application.handler.ITechnologyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TechnologyRestControllerTest {

    @Mock
    private ITechnologyHandler technologyHandler;

    private WebTestClient webTestClient;

    @InjectMocks
    private TechnologyRestController technologyRestController;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToController(technologyRestController).build();
    }

    @Test
    void createTechnology_ShouldReturnCreated() {
        TechnologyRequest request = new TechnologyRequest("Java", "Backend");
        TechnologyResponse response = new TechnologyResponse(1L, "Java", "Backend");

        when(technologyHandler.createTechnology(any(TechnologyRequest.class))).thenReturn(Mono.just(response));

        webTestClient.post()
                .uri("/technology/")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1);

        verify(technologyHandler).createTechnology(any(TechnologyRequest.class));
    }
    @Test
    void getTechnologyById_ShouldReturnTechnology() {
        TechnologyResponse response = new TechnologyResponse(1L, "Java", "Backend");

        when(technologyHandler.getTechnologyById(1L)).thenReturn(Mono.just(response));

        webTestClient.get()
                .uri("/technology/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Java");

        verify(technologyHandler).getTechnologyById(1L);
    }

    @Test
    void getAllTechnologies_ShouldReturnList() {
        TechnologyResponse response = new TechnologyResponse(1L, "Java", "Backend");

        when(technologyHandler.getTechnologies()).thenReturn(Flux.just(response));

        webTestClient.get()
                .uri("/technology/")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].description").isEqualTo("Backend");

        verify(technologyHandler).getTechnologies();
    }
}