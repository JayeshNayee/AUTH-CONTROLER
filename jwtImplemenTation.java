package com.newVjwt.implemenTation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.newVjwt.RepositryInterface.RepositryInterFace;
import com.newVjwt.loginEntity.login;

@Component
public class jwtImplemenTation implements UserDetailsService {

	@Autowired
	private RepositryInterFace repositryInterFace;
	
	@Bean
	public PasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) {
			login login = repositryInterFace.findById(username).get();
			System.out.println("Login data "+login);
			if (login.getUserName()!=null) {
				
			  String role=	username.equals("jayesh") ? "ROLE_ADMIN" : "ROLE_NORMAL";
			  List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(role);
			  
			  System.out.println(role);
			  return new User(login.getUserName(), passwordEncoder().encode(login.getPassWord()), authorityList);
				
			}
			
//			return new User(login.getUserName(), login.getPassWord(), new ArrayList<>());
			throw new UsernameNotFoundException(username + " Is In Not in Databse.!!!!!!");	
	}

}
