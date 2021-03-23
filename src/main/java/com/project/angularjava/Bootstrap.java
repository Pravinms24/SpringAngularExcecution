package com.project.angularjava;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.angularjava.service.RegistrationService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Bootstrap {
	@Autowired
	RegistrationService registrationService;
	//static Logger log = Logger.getLogger(Bootstrap.class);

	@PostConstruct
	public void initialize() {
		try {
			log.info("Bootstrap Init--->");
			//registrationService.populateRole();
			registrationService.populateDefaultData();
		} catch (Exception e) {
			log.error("ootstrap Error ->"+e);
			//log.info("Error Occured in init" + e);
		}

	}

}
