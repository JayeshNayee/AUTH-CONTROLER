package com.newVjwt.entryPoint;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class jwtEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		   response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		PrintWriter Writer = response.getWriter();
		
		Writer.println(" Access Denied  "+ authException.getMessage());
	}

}
