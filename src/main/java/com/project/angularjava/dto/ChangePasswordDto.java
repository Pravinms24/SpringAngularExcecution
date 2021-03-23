package com.project.angularjava.dto;

import java.io.Serializable;

import com.project.angularjava.model.Role;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude= {"password","confirmPassword"})
public class ChangePasswordDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	String email;
	String password;
	String confirmPassword;
	String fullname;
	Boolean enabled = true;
	private Role role; 

}
