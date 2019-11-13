package com.parkson.assignment.advice;

import com.parkson.assignment.exception.UnProcessAbleEntity;
import com.parkson.assignment.utils.GenericResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private MessageSource messages;

  @Autowired
  public RestResponseEntityExceptionHandler(final MessageSource messageSource) {
    super();
    this.messages = messageSource;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.error("400 Status Code", ex);
    final BindingResult result = ex.getBindingResult();
    final GenericResponse bodyOfResponse =
        new GenericResponse(result.getAllErrors(), "Invalid" + result.getObjectName());
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  // 404
  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<Object> handleUserNotFound(
      final RuntimeException ex, final WebRequest request) {
    logger.error("404 Status Code", ex);
    final GenericResponse bodyOfResponse =
        new GenericResponse(
            messages.getMessage("company.master.not.found", null, request.getLocale()));
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  // 422
  @ExceptionHandler({UnProcessAbleEntity.class})
  public ResponseEntity<Object> handleUnProcessAbleEntity(
      final RuntimeException ex, final WebRequest request) {
    logger.error("422 Status Code", ex);
    final GenericResponse bodyOfResponse =
        new GenericResponse(
            messages.getMessage("company.mater.unprocessable.entity", null, request.getLocale()));
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleInternal(
      final RuntimeException ex, final WebRequest request) {
    logger.error("500 Status Code", ex);
    final GenericResponse bodyOfResponse =
        new GenericResponse(
            messages.getMessage("message.error", null, request.getLocale()), "InternalError");
    return new ResponseEntity<>(
        bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
