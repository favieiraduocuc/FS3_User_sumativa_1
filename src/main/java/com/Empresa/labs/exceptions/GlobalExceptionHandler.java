// com.Empresa.labs.web.GlobalExceptionHandler
package com.Empresa.labs.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, Object> body = new HashMap<>();
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
    body.put("error", "ValidationError");
    body.put("details", errors);
    return body;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public Map<String, Object> handleConstraint(ConstraintViolationException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "ConstraintViolation");
    body.put("details", ex.getMessage());
    return body;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public Map<String, Object> handleNotFound(NotFoundException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "NotFound");
    body.put("message", ex.getMessage());
    return body;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public Map<String, Object> handleGeneric(Exception ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "ServerError");
    body.put("message", ex.getMessage());
    return body;
  }
}
