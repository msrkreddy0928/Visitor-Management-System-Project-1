package com.example.VMSappdemo.Config;

import com.example.VMSappdemo.RestException.BadRequestException;
import com.example.VMSappdemo.RestException.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import com.example.VMSappdemo.Dto.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations= RestController.class)
public class RestException {

@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse>handleNotFoundException(final NotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setException(exception.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse>handleBadRequestException(BadRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setException(exception.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }




}

