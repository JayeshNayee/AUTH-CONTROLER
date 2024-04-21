package com.TokenGenerate.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class customException extends RuntimeException{
	
	private String exMessage;

}
