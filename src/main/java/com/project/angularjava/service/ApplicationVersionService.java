package com.project.angularjava.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.project.angularjava.dto.BaseDto;

@Service
@PropertySource("classpath:application.properties")
public class ApplicationVersionService {
	
	@Value("${application.version}")
	private String appVersion;
	
	public BaseDto getApplicationVersion() {
		BaseDto baseDto = new BaseDto();
		baseDto.setResponseObject(this.appVersion);
		
		return baseDto;
	}

}
