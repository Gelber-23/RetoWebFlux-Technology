package com.pragma.technology.infraestructure.exceptionhandler;

import com.pragma.technology.domain.exception.InvalidTechnologyException;
import com.pragma.technology.domain.exception.TechnologyAlreadyExitsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "Message";


    @ExceptionHandler(TechnologyAlreadyExitsException.class)
    public ResponseEntity<Map<String, String>> handleTechnologyAlreadyExist (
            TechnologyAlreadyExitsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(
                        MESSAGE,
                        ex.getMessage()
                ));
    }
    @ExceptionHandler(InvalidTechnologyException.class)
    public ResponseEntity<Map<String, String>> handleTechnologyValidation (
            InvalidTechnologyException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(
                        MESSAGE,
                        ex.getMessage()
                ));
    }
}
