package com.pragma.technology.infraestructure.configuration;

import com.pragma.technology.domain.api.ITechnologyServicePort;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.technology.domain.usecase.TechnologyUseCase;
import com.pragma.technology.infraestructure.output.jpa.adapter.TechnologyJpaAdapter;
import com.pragma.technology.infraestructure.output.jpa.mapper.ITechnologyEntityMapper;
import com.pragma.technology.infraestructure.output.jpa.repository.ITechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {


    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Bean
    public ITechnologyPersistencePort restaurantPersistencePort(){
        return new TechnologyJpaAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort(){
        return new TechnologyUseCase(restaurantPersistencePort());
    }
}
