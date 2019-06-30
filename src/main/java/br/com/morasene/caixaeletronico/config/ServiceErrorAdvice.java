package br.com.morasene.caixaeletronico.config;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceErrorAdvice {

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<String> handleRunTimeException(MethodArgumentNotValidException e) {
		StringBuilder errors = new StringBuilder();
		e.getBindingResult().getAllErrors().stream().forEach(er -> errors.append(er.getDefaultMessage()).append("\n"));
		return error(NOT_ACCEPTABLE, errors.toString());
	}
	
	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<String> handleException(IllegalArgumentException e) {
		return error(NOT_ACCEPTABLE, e.getMessage());
	}

	private ResponseEntity<String> error(HttpStatus status, String message) {
		return ResponseEntity.status(status).body(message);
	}
}
