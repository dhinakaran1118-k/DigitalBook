package com.books.exceptionhandler;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<ObjectError> errors = exception.getBindingResult().getAllErrors();
		System.out.println("bad request");
		Map<String, String> errorsMap = new HashMap<>();
		ListIterator<ObjectError> iterator = errors.listIterator();
		while (iterator.hasNext()) {
			ObjectError error = iterator.next();
			errorsMap.put(error.getCode(), error.getDefaultMessage());
		}
		return errorsMap;
	}
}
