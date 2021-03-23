package com.project.angularjava.converter;

import com.project.angularjava.dto.UserDto;
import com.project.angularjava.model.User;

public class UserToUserDtoConverter {
	
	public static UserDto user2UserDto (User user) {
		UserDto dto = null;
		
		if (user == null) {
			return dto;
		} else {
			dto = new UserDto();
			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setEnabled(user.isEnabled());
			dto.setFullname(user.getFullname());
			dto.setPassword(user.getPassword());
			if (user.getRoles() !=null && user.getRoles().size()>0) {
				dto.setRole(user.getRoles().iterator().next());
			}
		}
		
		return dto;	
	}
	
	public static User userDtotoUser(UserDto userDto) {
		
		return null;	
	}

}
