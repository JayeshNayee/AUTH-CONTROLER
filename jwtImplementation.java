package com.TokenGenerate.implementation;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TokenGenerate.Exception.customException;
import com.TokenGenerate.studentEntity.studentEntity;
import com.TokenGenerate.studentRepositry.Repositry;

@Service
public class jwtImplementation  implements UserDetailsService{
	
	@Autowired
	private Repositry repo;

	@Override
	public UserDetails loadUserByUsername(String username)throws customException{
		try {
				studentEntity studentEntity1 = repo.findById(username).get();
				
				return new User(studentEntity1.getUsername(), studentEntity1.getPassword(), new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
			throw new customException("This Username is not present !...");
		}

	}
	
	
	

}
