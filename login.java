package com.newVjwt.loginEntity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class login {
	@Id
	private String userName;
	private String passWord;
	
	@CreationTimestamp
	private Date Date;

}
