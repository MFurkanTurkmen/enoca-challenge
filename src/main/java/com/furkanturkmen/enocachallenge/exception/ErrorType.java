package com.furkanturkmen.enocachallenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(1000,"An Unknown error has occurred on the server", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"Bad Request",HttpStatus.BAD_REQUEST),
    MAIL_ALREADY_EXISTS(1020,"Mail Already Exists",HttpStatus.BAD_REQUEST),
    ID_NOT_FOUND(1021,"Id Not Found",HttpStatus.BAD_REQUEST),
    MAIL_NOT_FOUND(1022,"Mail Not Found",HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1023,"Product Not Found",HttpStatus.BAD_REQUEST),
    WRONG_TOKEN(1024,"Token is wrong",HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD(1025,"Password is wrong",HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatus httpStatus;
}