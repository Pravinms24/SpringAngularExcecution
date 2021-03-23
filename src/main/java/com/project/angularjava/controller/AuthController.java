package com.project.angularjava.controller;

import static org.springframework.http.ResponseEntity.ok;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.angularjava.config.JwtTokenProvider;
import com.project.angularjava.dto.BaseDto;
import com.project.angularjava.dto.LoginDto;
import com.project.angularjava.dto.UserDto;
import com.project.angularjava.model.User;
import com.project.angularjava.repository.UserRepository;
import com.project.angularjava.service.CustomUserDetailsService;
import com.project.angularjava.service.LoginAuthService;



//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="/api/auth")
public class AuthController {

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
	LoginAuthService loginservice;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public BaseDto login (@RequestBody LoginDto logindto, HttpServletRequest request) {
		BaseDto baseDto = loginservice.login(logindto);
		System.out.println("baseDto"+baseDto);
		return baseDto;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody User user) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
		}
			userService.saveUser(user);
			Map<Object, Object> model = new HashMap<>();
			model.put("email", user.getEmail());
			model.put("message", "User Registerd Sucessfully");
			return ok(model);
	}


}