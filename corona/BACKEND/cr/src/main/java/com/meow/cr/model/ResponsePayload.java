package com.meow.cr.model;

public class ResponsePayload {
	public Integer code;
	public String message;
	public Boolean success;
	public Object data;

	public ResponsePayload(Integer code, String message, Boolean success, Object data) {
		this(code, message, success);
		this.data = data;
	}


	public ResponsePayload(Integer code, String message, Boolean success) {
		this(code, message);
		this.success = success;
	}

	public ResponsePayload(Integer code, String message) {
		this.code = code;
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
