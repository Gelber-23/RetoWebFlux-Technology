package com.pragma.technology.infraestructure.output.jpa.adapter;

import com.pragma.technology.domain.model.Technology;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.technology.infraestructure.output.jpa.mapper.ITechnologyEntityMapper;
import com.pragma.technology.infraestructure.output.jpa.repository.ITechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class TechnologyJpaAdapter implements ITechnologyPersistencePort {


    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Override
    public Mono<Technology> getTechnologyById(Long id) {
        return technologyRepository.findById(id).map(technologyEntityMapper::toModel);
    }

    @Override
    public Flux<Technology> getTechnologies() {
        return technologyRepository.findAll().map(technologyEntityMapper::toModel);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return technologyRepository.existsByName(name);
    }

    @Override
    public Mono<Technology> createTechnology(Technology technology) {
        return  technologyRepository.save(technologyEntityMapper.toEntity(technology)).map(technologyEntityMapper::toModel);

    }

    @Override
    @Transactional
    public Mono<Void> deleteTechnologyById(Long id) {
        return technologyRepository.deleteById(id);
    }
}
