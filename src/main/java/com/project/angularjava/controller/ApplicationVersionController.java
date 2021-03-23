package com.project.angularjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.angularjava.dto.BaseDto;
import com.project.angularjava.service.ApplicationVersionService;

@RestController
@RequestMapping(value="application")
@CrossOrigin(origins = "*")
public class ApplicationVersionController {
	
	@Autowired
	ApplicationVersionService applicationVersionService;
	
	@RequestMapping(value="/getversion", method=RequestMethod.GET)
	public BaseDto getAppVersion() {
		System.out.println("called");
		return applicationVersionService.getApplicationVersion();
		
	}

}
