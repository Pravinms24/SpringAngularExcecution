package com.project.angularjava.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.angularjava.enumeration.CodeDescription;

import lombok.Data;

@Data
public class BaseDto {
	
	String responseDescription;
	Object responseObject;
	boolean status ;
	Integer responseCode;
	List<String> params = new ArrayList<String>();
	
	
	public BaseDto (Integer code, String description, boolean status, Object response) {
		this.responseCode = code;
		this.responseDescription = description;
		this.status = status;
		this.setResponseObject(response);
	}
	public BaseDto (CodeDescription codeDescription, Object description) {
		this.responseCode = codeDescription.getCode();
		this.setResponseObject(description);
	}
	
	public BaseDto() {
		
	}
	
}
