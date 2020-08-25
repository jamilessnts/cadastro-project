package br.com.jamilesantos.cadastro.exception;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> dataNotFoundException(CustomNotFoundException e, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> forAllExceptions(Exception e, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
