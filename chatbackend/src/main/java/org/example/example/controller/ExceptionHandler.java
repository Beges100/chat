package org.example.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.tool.schema.internal.ExceptionHandlerCollectingImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ExceptionHandlingWebHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var dto = new ApiDto();
        dto.setException(ex.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
    }



}

@Getter
@Setter
class ApiDto {
    private String exception;
}

