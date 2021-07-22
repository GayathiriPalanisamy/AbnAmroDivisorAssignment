package com.abnamro.divisorassignment.exception;

import java.net.http.HttpRequest;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.abnamro.divisorassignment.bean.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ControllerAdvice
//@ApiOperation(response = ResultCode.class, value = "StandardErrorResponse")
public class StandardErrorResponse extends ExceptionHandlerExceptionResolver{

	//custom exception when the numberValue is negative integer
	@ExceptionHandler(NotPositiveIntegerException.class)
	public final ResponseEntity<Object> handleNegativeIntegerFormatException(Exception ex, HttpServletRequest request) 
	{
		ResultCode resultCode=new ResultCode("Enter Positive Integer ","Error: numberValue should be >= 1",request.getAttribute("requestId").toString());
		return new ResponseEntity(resultCode,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NumberFormatException.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, HttpServletRequest request) 
	{
		ResultCode resultCode=new ResultCode("INVALID REQUEST", ex.getMessage(),null);
		return new ResponseEntity(resultCode,HttpStatus.BAD_REQUEST);
	}
	

}
