package com.project.angularjava.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.angularjava.config.JwtTokenProvider;
import com.project.angularjava.converter.UserToUserDtoConverter;
import com.project.angularjava.dto.BaseDto;
import com.project.angularjava.dto.LoginDto;
import com.project.angularjava.dto.LoginResponseDto;
import com.project.angularjava.dto.UserDto;
import com.project.angularjava.enumeration.CodeDescription;
import com.project.angularjava.exception.RestException;
import com.project.angularjava.model.User;
import com.project.angularjava.repository.UserRepository;
import com.project.angularjava.util.Validate;

@Service
public class LoginAuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;

	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public BaseDto login(LoginDto logindto)  {
		
		BaseDto baseDto = new BaseDto();
		try {
			validate(logindto);
			String username = logindto.getEmail();
			User name = users.findByEmail(username);
		if (name == null) {
			baseDto.setResponseCode(CodeDescription.USER_NAME_INVALID.getCode());
			baseDto.setResponseDescription(CodeDescription.USER_NAME_INVALID.getDescription());
			baseDto.setStatus(false);
			return baseDto;
		} else {
			if (!name.isEnabled()) {
				//throw new RestException(CodeDescription.USER_DISABLED.getDescription());
				baseDto.setResponseCode(CodeDescription.USER_DISABLED.getCode());
				baseDto.setResponseDescription(CodeDescription.USER_DISABLED.getDescription());
				baseDto.setStatus(false);
				return baseDto;
			}		
		}
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, logindto.getPassword()));
		String token = jwtTokenProvider.createToken(username, this.users.findByEmail(username).getRoles());
		
		LoginResponseDto respDto = new LoginResponseDto();
		respDto.setUserId(name.getId());
		respDto.setEmail(name.getEmail());
		respDto.setUserName(name.getFullname());
		respDto.setActive(name.isEnabled());
		respDto.setRole(name.getRoles().iterator().next());
		respDto.setJwt(token);
		baseDto.setResponseCode(CodeDescription.LOGIN_SUCCESS.getCode());
		baseDto.setResponseDescription(CodeDescription.LOGIN_SUCCESS.getDescription());
		baseDto.setStatus(true);
		baseDto.setResponseObject(respDto);
	} catch (AuthenticationException e) {
//		throw new BadCredentialsException("Invalid email/password supplied");
		baseDto.setResponseCode(CodeDescription.PASSWORD_INVALID.getCode());
		baseDto.setResponseDescription(CodeDescription.PASSWORD_INVALID.getDescription());
		baseDto.setStatus(false);
		return baseDto;
	}
		
		return baseDto;		
	}
	
	private void validate (LoginDto loginDto) {
		
		if (loginDto == null || loginDto.getEmail() == null || loginDto.getEmail() == "") {
			
			throw new RestException(CodeDescription.USER_NAME_MANDATORY);
		} else if (loginDto == null || loginDto.getPassword() == null || loginDto.getPassword() == "") {
			throw new RestException(CodeDescription.PASSWORD_MANDATORY);
		}
		
	}
	
	public BaseDto changePassword (UserDto userDto) {
		Validate.nullCheck(userDto, CodeDescription.USER_NAME_MANDATORY);
		Validate.nullCheck(userDto.getEmail(), CodeDescription.USER_NAME_MANDATORY);
		Validate.nullCheck(userDto.getPassword(), CodeDescription.PASSWORD_MANDATORY);
		System.out.println("userDto"+userDto);
		
		BaseDto baseDto = new BaseDto();
		
		User user = users.findByEmail(userDto.getEmail());
		if(user !=null) {
			System.out.println(bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword()));
			if(!bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {			
				user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
				
				users.save(user);		
				baseDto.setResponseDescription(CodeDescription.PASSWORD_RESET.getDescription());
				baseDto.setStatus(true);
				baseDto.setResponseObject(users);
			}
			else {
				System.out.println("did not match");
				baseDto.setResponseDescription(CodeDescription.SAME_PASSWORD.getDescription());
				baseDto.setStatus(false);
				baseDto.setResponseObject(null);
			}	
		}

		return baseDto;		
	}
	
	public BaseDto getUserById (String id) {
		BaseDto baseDto = new BaseDto();
		try {
			Optional<User> user = users.findById(id);
			if (user.isPresent()) {
				UserDto userDto = UserToUserDtoConverter.user2UserDto(user.get());
				baseDto = new BaseDto(CodeDescription.SUCCESS.getCode(),CodeDescription.SUCCESS.getDescription(),true, userDto);		
			} else {
				baseDto=new BaseDto(CodeDescription.USER_NOT_FOUND, null);
			}
			
		} catch (RestException e) {
			baseDto.setResponseCode(e.getCode());
			baseDto.setResponseDescription(e.getDescription());
		} catch (Exception e) {
			baseDto.setResponseCode(CodeDescription.FAILED.getCode());
			baseDto.setResponseDescription(e.getMessage());
		}
		
		return baseDto;		
	}

	
}
