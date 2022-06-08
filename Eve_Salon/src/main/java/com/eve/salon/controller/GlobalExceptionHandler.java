package com.eve.salon.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eve.salon.entity.exceptions.CustomerAlreayExits;
import com.eve.salon.entity.exceptions.CustomerNotFoundException;
import com.eve.salon.entity.exceptions.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		List<String> errorList = new ArrayList<>();

		errorList = ex.getFieldErrors().stream().map(e1 -> e1.getField() + "::" + e1.getDefaultMessage())
				.collect(Collectors.toList());

		ErrorMessage messages = new ErrorMessage(new Date(), "Validation Errors", "", errorList);

		return new ResponseEntity<Object>(messages, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerAlreayExits.class)
	public ResponseEntity<ErrorMessage> handleCustomerAlreayExits(CustomerAlreayExits ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(new Date(), "Customer Already exists", ex.getMessage(), null);

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(CustomerNotFoundException ex,
			WebRequest request) {
		ErrorMessage message = new ErrorMessage(new Date(), "Customer is Not Available", ex.getMessage(), null);

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

	}

}