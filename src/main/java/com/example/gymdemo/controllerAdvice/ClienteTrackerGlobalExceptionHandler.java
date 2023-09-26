package com.example.gymdemo.controllerAdvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.gymdemo.exceptions.ClienteNotFoundException;

public class ClienteTrackerGlobalExceptionHandler extends
ResponseEntityExceptionHandler {
	@ExceptionHandler(value = {ClienteNotFoundException.class})
	public ResponseEntity<?> handleClienteNotFound(
			ClienteNotFoundException clienteNotFoundException,
			WebRequest request){
		return super.handleExceptionInternal(
				clienteNotFoundException,
				clienteNotFoundException.getMessage(),
				new HttpHeaders(),
				HttpStatus.NOT_FOUND,
				request
				);
	}
	

}
