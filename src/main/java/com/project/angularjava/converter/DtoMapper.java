package com.project.angularjava.converter;

import com.project.angularjava.dto.RoleDto;
import com.project.angularjava.model.Role;

public class DtoMapper {
	
	public static RoleDto roleToRoleDto (Role role) {
		RoleDto roleDto = new RoleDto();
		roleDto.setId(role.getId());
		roleDto.setRole(role.getRole());
		return roleDto;
		
	}

}
