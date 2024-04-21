package com.newVjwt.jwtRequestResponce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class jwtRequest {
	private String userName;
	private String passWord;
}
