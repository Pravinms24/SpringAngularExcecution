package com.project.angularjava.util;

import com.project.angularjava.enumeration.CodeDescription;
import com.project.angularjava.exception.RestException;

import org.springframework.util.StringUtils;

public class Validate {

	@SuppressWarnings("deprecation")
	public static void nullCheck(Object object, CodeDescription codeDescription) {
		if (object == null || StringUtils.isEmpty(object)) {
			System.out.println("object is" + object);
			throw new RestException(codeDescription);
		}
	}

}
