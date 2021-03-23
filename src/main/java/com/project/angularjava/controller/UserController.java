package com.project.angularjava.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.angularjava.dto.BaseDto;
import com.project.angularjava.dto.PagenationRequestDto;
import com.project.angularjava.dto.UserDto;
import com.project.angularjava.repository.UserRepository;
import com.project.angularjava.service.LoginAuthService;
import com.project.angularjava.service.UserService;

@RestController
@RequestMapping(value="user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	LoginAuthService loginservice;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public BaseDto resetPassword (@RequestBody UserDto userDto, HttpServletRequest request) {
		System.out.println("baseDto"+userDto);
		BaseDto baseDto = loginservice.changePassword(userDto);		
		return baseDto;
	}
	
	@RequestMapping(value="/getById/{id}",method=RequestMethod.GET)
	public BaseDto getUserById(@PathVariable("id") String id, HttpServletRequest request) {
		System.out.println("baseDto"+id);
		BaseDto baseDto = new BaseDto()	;
		System.out.println(baseDto);
		baseDto = loginservice.getUserById(id);
		System.out.println(baseDto);
		return baseDto;		
	}
	
	@RequestMapping(value="/getallusers",method=RequestMethod.POST)
	public BaseDto getAllUsers(@RequestBody PagenationRequestDto pagenationRequest,HttpServletRequest request) {
		
		BaseDto baseDto = new BaseDto()	;
		System.out.println(baseDto);
		baseDto = userService.getAllUsers(pagenationRequest);
		System.out.println(baseDto);
		return baseDto;		
	}
	
	@RequestMapping(value="/createUser",method=RequestMethod.POST)
	public BaseDto createUser(@RequestBody UserDto userDto, HttpServletRequest request) {	
		BaseDto baseDto = userService.createUser(userDto);	
		return baseDto;	
	}
	
	@RequestMapping(value="/getroles",method=RequestMethod.GET)
	public BaseDto getRole(HttpServletRequest request) {
		
		BaseDto baseDto = userService.getRoles();
		return baseDto;		
	}

}
