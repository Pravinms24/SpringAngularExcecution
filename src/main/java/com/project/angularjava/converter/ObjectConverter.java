package com.project.angularjava.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter {
	
	public static <T> T convertObject (Object source, Class<T> target ) {
		ObjectMapper mapper = new ObjectMapper();
		T object = mapper.convertValue(source, target);
		return object;		
	}

}
