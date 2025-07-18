package com.pragma.technology.infraestructure.input.res;

import com.pragma.technology.application.dto.request.TechnologyRequest;
import com.pragma.technology.application.dto.response.TechnologyResponse;
import com.pragma.technology.application.handler.ITechnologyHandler;
import com.pragma.technology.domain.util.OpenApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/technology")
@Tag(name = OpenApiConstants.TITLE_TECHNOLOGY_REST, description = OpenApiConstants.TITLE_DESCRIPTION_TECHNOLOGY_REST)
@RequiredArgsConstructor
public class TechnologyRestController {

    private final ITechnologyHandler technologyHandler;

    @Operation(summary = OpenApiConstants.NEW_TECHNOLOGY_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_201, description = OpenApiConstants.NEW_TECHNOLOGY_CREATED_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_400, description = OpenApiConstants.VALIDATIONS_ERRORS_MESSAGE, content = @Content)
    })
    @PostMapping("/")
    public Mono<TechnologyResponse> createTechnology (@RequestBody TechnologyRequest technologyRequest) {
        return  technologyHandler.createTechnology(technologyRequest);
    }

    @Operation(summary = OpenApiConstants.GET_TECHNOLOGY_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_200, description = OpenApiConstants.GET_TECHNOLOGY_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_400, description = OpenApiConstants.VALIDATIONS_ERRORS_MESSAGE, content = @Content)
    })
    @GetMapping("/{id}")
    public Mono<TechnologyResponse> getTechnologyById(@PathVariable(value = "id") Long id) {
        return technologyHandler.getTechnologyById(id);
    }

    @Operation(summary = OpenApiConstants.GET_ALL_TECHNOLOGY_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_200, description = OpenApiConstants.GET_ALL_TECHNOLOGY_MESSAGE,
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TechnologyResponse.class)))),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_404, description =OpenApiConstants.NO_DATA_MESSAGE, content = @Content)
    })
    @GetMapping("/")
    public Flux<TechnologyResponse> getAllTechnologies(){

        return  technologyHandler.getTechnologies();
    }




}
