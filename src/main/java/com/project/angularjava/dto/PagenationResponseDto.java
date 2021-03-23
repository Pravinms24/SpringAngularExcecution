package com.project.angularjava.dto;

import lombok.Data;

@Data
public class PagenationResponseDto {
	
	Long totalElements;

	int totalPages;

	int numberOfElements;

	Object content;

	public PagenationResponseDto(Long totalElements,int totalPages,int numberOfElements,Object content){
		this.totalElements=totalElements;
		this.totalPages=totalPages;
		this.numberOfElements=numberOfElements;
		this.content=content;
	}

}
