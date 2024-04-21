package com.TokenGenerate.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.TokenGenerate.implementation.jwtImplementation;
import com.TokenGenerate.jwtRequestFilter.jwtReqFilter;

@Configuration
@EnableWebSecurity
public class webConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private jwtImplementation jwtImplementatio1;
	@Autowired
	private jwtReqFilter jwtReqFilter1;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtImplementatio1);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// /get-token
		http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/SaveData").permitAll().anyRequest()
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtReqFilter1, UsernamePasswordAuthenticationFilter.class);
	}


	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {

		return super.authenticationManager();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
