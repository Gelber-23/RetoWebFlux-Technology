package com.pragma.technology.domain.exception;

public class TechnologyAlreadyExitsException  extends RuntimeException {

    public TechnologyAlreadyExitsException(String message) {
        super(message);
    }

}