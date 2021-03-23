package com.project.angularjava.exception;

import com.project.angularjava.enumeration.CodeDescription;

import lombok.Getter;

public class RestException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	Integer code;
	@Getter
	String description;
	
	public RestException() {
		
	}
	
	public RestException(CodeDescription codeDescription) {
		this.description = codeDescription.getDescription();
		this.code = codeDescription.getCode();
	}
	
	public RestException(String codeDescription) {
		this.description = codeDescription;
	}
	
	@Override
    public String toString() {
        return "Exception [code=" + code + ", message=" + description + "]";
    }

}
