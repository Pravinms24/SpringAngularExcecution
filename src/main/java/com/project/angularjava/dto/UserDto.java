package com.project.angularjava.dto;

import java.io.Serializable;

import com.project.angularjava.model.Address;
import com.project.angularjava.model.Role;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="password")
public class UserDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String email;
	String password;
	String fullname;
	String phoneNumber;
	boolean enabled;
	Address address ;
	Role role; 
	
	
	

}
