package com.radzik.michal.shop.user.controller;

import com.radzik.michal.shop.user.domain.dto.ErrorDto;
import com.radzik.michal.shop.user.domain.dto.FieldErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j

public class AdviceController {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDto handleBadCreditentialException(BadCredentialsException badCredentialsException) {
        log.error("BadCredentials exception", badCredentialsException);
        return new ErrorDto("Bad Credential");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        log.error("Entity not found exception", entityNotFoundException);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleEntityNotFoundException(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        log.error("Duplicate entry ", sqlIntegrityConstraintViolationException);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<FieldErrorDto> handleBadRequestException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("Invalid argument ", methodArgumentNotValidException);
        methodArgumentNotValidException.getMessage();
        List<FieldErrorDto> fieldErrorDtoStream = methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map(objectError1 -> {
                    if (objectError1 instanceof FieldError fieldError) {
                       return new FieldErrorDto(fieldError.getField(), objectError1.getDefaultMessage());
                    }
                    return new FieldErrorDto(null, objectError1.getDefaultMessage());
                })
                .collect(Collectors.toList());

        return fieldErrorDtoStream;
}
}
