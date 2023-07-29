package com.furkanturkmen.enocachallenge.exception;

import lombok.Getter;


@Getter
public class EnocaException extends RuntimeException {
    private final ErrorType errorType;
    public EnocaException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public EnocaException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }


}
