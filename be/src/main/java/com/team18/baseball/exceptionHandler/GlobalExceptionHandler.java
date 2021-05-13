package com.team18.baseball.exceptionHandler;

import com.team18.baseball.exception.NotFoundException;
import com.team18.baseball.response.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseBody<Object> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseBody.notFound(notFoundException.getMessage());
    }
}
