package com.abnamro.divisorassignment.bean;

import java.util.UUID;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ResultCode {
	private String message;	
	private String code;	
	
	private String requestID;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public ResultCode(String message, String code, String requestID) {
		super();
		this.message = message;
		this.code = code;
		this.requestID = requestID;
	}
	public ResultCode() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ResultCode [message=" + message + ", code=" + code + ", requestID=" + requestID + "]";
	}
	
}
