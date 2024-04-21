package com.newVjwt.webConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.newVjwt.entryPoint.jwtEntryPoint;
import com.newVjwt.filter.filter;

@Configuration
@EnableWebSecurity
public class webCofiguration {

	@Autowired
	private filter filter1;

	@Autowired
	private jwtEntryPoint jwtEntryPoint1;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(requests -> requests
						.requestMatchers("/loginApi").hasRole("ADMIN")
						.requestMatchers("/get-token").permitAll()
						.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtEntryPoint1))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(filter1, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration axConf)
			throws Exception {
		return axConf.getAuthenticationManager();

	}

	
//	  @Bean 
//	  public PasswordEncoder passwordEncoder() { return new
//	  BCryptPasswordEncoder(); }
	 
	 
}
