package com.project.angularjava.enumeration;

public enum CodeDescription {
	
	SUCCESS(200,"Response Success"),
	FAILED(201,"Response Failure"),
	REST_EXCEPTION(202,"Rest Exception"),
	
	PAGINATION_PAGE_EMPTY(301,"Pagination request page is empty"),
	PAGINATION_SIZE_EMPTY(302,"Pagination request size is empty"),
	PAGINATION_SORT_FIELD_EMPTY(303,"Pagination request sort field is empty"),
	PAGINATION_SORT_ORDER_EMPTY(304,"Pagination request sort order is empty"),
	
	USER_NAME_MANDATORY(10001,"User name cannot be empty"),
	PASSWORD_MANDATORY(10002,"Password cannot be empty"),
	USER_ID_MANDATORY(10001,"User ID cannot be empty"),
	USER_SAVED_SUCCESSFULLY(10003,"User saved successfully"),
	USER_UPDATED_SUCCESSFULLY(10004,"User updated successfully"),
	USER_NAME_EXIST(10005,"User name already exist"),	
	PASSWORD_INVALID(100021,"Please kindly check password"),
	USER_NAME_INVALID(100022,"User name is not registered"),
	LOGIN_SUCCESS(100023,"You have successfully logged in"),
	USER_DISABLED(100024,"User login is restricted"),
	PASSWORD_RESET(10025, "Password Changed Sucessfully"),
	USER_NOT_FOUND(10026, "User Not Found"),
	SAME_PASSWORD(10027,"Old password should not same as new"),
	USER_EXISTS(10026,"User Already presents with same email id")
	
	;
	
	
	Integer code;
	String description;
	
	private CodeDescription() {}
	
    private CodeDescription(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    private CodeDescription(int code, String description, String parameter) {
        this.code = code;
        this.description = description+" :'"+parameter+"'";
    }
    
	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	

}
