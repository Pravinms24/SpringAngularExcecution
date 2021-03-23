package com.project.angularjava.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.angularjava.converter.DtoMapper;
import com.project.angularjava.converter.ObjectConverter;
import com.project.angularjava.dto.BaseDto;
import com.project.angularjava.dto.PagenationRequestDto;
import com.project.angularjava.dto.RoleDto;
import com.project.angularjava.dto.UserDto;
import com.project.angularjava.enumeration.CodeDescription;
import com.project.angularjava.exception.RestException;
import com.project.angularjava.model.Role;
import com.project.angularjava.model.User;
import com.project.angularjava.repository.RoleRepository;
import com.project.angularjava.repository.UserRepository;
import com.project.angularjava.util.Paginate;
import com.project.angularjava.util.Validate;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public BaseDto createUser(UserDto userDto) {
		Validate.nullCheck(userDto, CodeDescription.USER_NOT_FOUND);
		Validate.nullCheck(userDto.getEmail(), CodeDescription.USER_NOT_FOUND);
		Validate.nullCheck(userDto.getRole(), CodeDescription.USER_NOT_FOUND);
		System.out.println("ss" + userDto);
		BaseDto baseDto = new BaseDto();
		try {
			User user = userRepository.findByEmail(userDto.getEmail());
			if (user == null) {
				User users = new User();
				users.setEmail(userDto.getEmail());
				users.setFullname(userDto.getFullname());
				users.setPhoneNumber(userDto.getPhoneNumber());
				users.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
				users.setEnabled(true);
				users.setAddress(userDto.getAddress());
				System.out.println(userDto.getRole().getRole());
				Role userRole = roleRepository.findByRole(userDto.getRole().getRole());
				users.setRoles(new HashSet<>(Arrays.asList(userRole)));
				System.out.println(userDto);
				System.out.println(users);
				userRepository.save(users);

			} else {
				baseDto = new BaseDto(CodeDescription.USER_EXISTS.getCode(),
						CodeDescription.USER_EXISTS.getDescription(), true, null);
			}
		} catch (RestException e) {

		}
		return baseDto;
	}

	public BaseDto getRoles() {
		BaseDto baseDto = new BaseDto();

		try {
			List<Role> roleList = roleRepository.findAll();
			List<RoleDto> roleDto = new ArrayList<>();

			for (Role role : roleList) {
				roleDto.add(DtoMapper.roleToRoleDto(role));
			}
			baseDto = new BaseDto(CodeDescription.SUCCESS.getCode(), CodeDescription.SUCCESS.getDescription(), true,
					roleDto);
		} catch (Exception e) {
			baseDto = new BaseDto(CodeDescription.FAILED.getCode(), CodeDescription.FAILED.getDescription(), false,
					null);
		}
		return baseDto;
	}

	public BaseDto getAllUsers(PagenationRequestDto pagenationRequest) {
		BaseDto baseDto = new BaseDto();
		Pageable pageable = Paginate.getPageable(pagenationRequest);
		UserDto userDto = ObjectConverter.convertObject(pagenationRequest.getRequestObject(), UserDto.class);
		if (userDto == null) {
			userDto = new UserDto();
		}

		try {
			Page<User> userData = userRepository.findAll(userDto.getFullname() == null ? "" : userDto.getFullname(), pageable);
			baseDto.setResponseObject(userData);
			baseDto.setStatus(true);

		} catch (RestException e) {
			baseDto.setResponseCode(e.getCode());
			baseDto.setResponseDescription(e.getDescription());
		} catch (Exception e) {
			baseDto.setResponseCode(CodeDescription.FAILED.getCode());
			baseDto.setResponseDescription(e.getMessage());
		}

		baseDto.setResponseDescription(CodeDescription.SUCCESS.getDescription());

		return baseDto;
	}

}
