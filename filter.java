package com.newVjwt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.newVjwt.implemenTation.jwtImplemenTation;
import com.newVjwt.jwtUtil.jwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Configuration
public class filter extends OncePerRequestFilter{

	@Autowired private	jwtUtil jwtUtil;
	
	@Autowired private jwtImplemenTation userDetailsService;

	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails normalUser=User.builder().username("user").password(passwordEncoder().encode("123")).build(); 
//		return new InMemoryUserDetailsManager(normalUser);
//
//	}	
//	@Bean 
//	  public PasswordEncoder passwordEncoder() { return new
//	  BCryptPasswordEncoder(); }
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token  = null;
		String username = null;

		String header = request.getHeader("Authorization");
		
		if(header!= null && header.startsWith("Bearer "))
		{
				token = header.substring(7);
				username = jwtUtil.extractUsername(token);
		}
		
		if(username != null)
		{
			UserDetails userDetails =this.userDetailsService.loadUserByUsername(username);
			
			if(userDetails!= null && jwtUtil.validateToken(token, userDetails) && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				UsernamePasswordAuthenticationToken upauToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
				
				upauToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
				SecurityContextHolder.getContext().setAuthentication(upauToken);
			}
		}
		filterChain.doFilter(request, response);

		
	}
	
	
	
	

}
