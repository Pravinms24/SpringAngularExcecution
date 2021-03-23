package com.project.angularjava.dto;

import com.project.angularjava.model.Role;

import lombok.Data;

@Data
public class LoginResponseDto {
	String userId;
	String email;
	String userName;
	Role role;
	boolean active;
	String jwt;
	

}
