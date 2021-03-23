package com.project.angularjava.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class PagenationRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Integer page;
	
	Integer size;
	
	String sortField;
	
	String sortOrder;
	
	Object requestObject;
	
	String searchTerm;

}
