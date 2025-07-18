package com.pragma.technology.infraestructure.output.jpa.repository;

import com.pragma.technology.infraestructure.output.jpa.entity.TechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ITechnologyRepository  extends ReactiveCrudRepository<TechnologyEntity,Long> {
    Mono<Boolean> existsByName(String name);
}
