package com.project.angularjava.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.project.angularjava.dto.PagenationRequestDto;
import com.project.angularjava.enumeration.CodeDescription;

public class Paginate {

	public static Pageable getPageable(PagenationRequestDto pagenationRequest) {
		Validate.nullCheck(pagenationRequest, CodeDescription.PAGINATION_PAGE_EMPTY);
		Validate.nullCheck(pagenationRequest.getPage(), CodeDescription.PAGINATION_PAGE_EMPTY);
		Validate.nullCheck(pagenationRequest.getSize(), CodeDescription.PAGINATION_SIZE_EMPTY);
		Validate.nullCheck(pagenationRequest.getSortField(), CodeDescription.PAGINATION_SORT_FIELD_EMPTY);
		Validate.nullCheck(pagenationRequest.getSortOrder(), CodeDescription.PAGINATION_SORT_ORDER_EMPTY);

		Sort.Direction sort = Sort.Direction.DESC;
		if (pagenationRequest.getSortOrder() != null && pagenationRequest.getSortOrder().equalsIgnoreCase("asc")) {
			sort = Sort.Direction.ASC;
		}

		return PageRequest.of(pagenationRequest.getPage(), pagenationRequest.getSize(),
				Sort.by(sort, pagenationRequest.getSortField()));
	}

}
