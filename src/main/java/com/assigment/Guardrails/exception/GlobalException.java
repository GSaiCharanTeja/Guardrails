package com.assigment.Guardrails.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assigment.Guardrails.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleResponseExceptionEntity(RuntimeException ex){
		ErrorResponse er=new ErrorResponse(ex.getMessage(),400);
		return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
	}
}
