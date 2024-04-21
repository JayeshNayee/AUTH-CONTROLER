package com.TokenGenerate.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class globalException {
		@ExceptionHandler(UsernameNotFoundException.class)
		public ResponseEntity<formateException>exception(UsernameNotFoundException uEx){
			
			formateException formateException  = new formateException();
			
			formateException.setExceptionMessage(uEx.getMessage());
			
			return new ResponseEntity<formateException>(formateException,HttpStatus.BAD_GATEWAY);
			
		}
		
		@ExceptionHandler(customException.class)
		public ResponseEntity<formateException>myCustomException(customException cuex){

			return new ResponseEntity<formateException>(new formateException(cuex.getExMessage()), HttpStatus.BAD_REQUEST);
			
		}
		
	
}
