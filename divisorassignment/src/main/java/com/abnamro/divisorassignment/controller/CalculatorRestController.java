package com.abnamro.divisorassignment.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.abnamro.divisorassignment.bean.EvenOrOdd;
import com.abnamro.divisorassignment.bean.NumberArrayResult;
import com.abnamro.divisorassignment.bean.ResultCode;
import com.abnamro.divisorassignment.exception.NotPositiveIntegerException;
import com.abnamro.divisorassignment.exception.StandardErrorResponse;
import com.abnamro.divisorassignment.service.CalculatorService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/calculator")
@Api(tags = "divisibilityCalculatorService" , description = "Services for performing custom calculations" )//basepath="/calculator"
public class CalculatorRestController {
	@Autowired
	CalculatorService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorRestController.class);
	private static String CLASSNAME = CalculatorRestController.class.getSimpleName();
	
	@GetMapping("/findDivisors/{numberValue}")
	
	@ApiOperation(value = "To find divisor of the given number",
	  nickname = "getDivisorsForGivenNumber",
	  notes = "This is a function where you can provide a positive integer in the URL path. The response will contain all whole numbers from 1 to numbervalue/2 by which the provided number can be divided (with no remainder) ",
	  tags="divisibilityCalculatorService",
	  produces = "application/json",
	  consumes = "application/json"
	  )
	@ApiResponses(value = {	@ApiResponse(code = 200, message = "Successful Response",response = NumberArrayResult.class),
			@ApiResponse(code = 401, message = "Unauthorized",response = ResultCode.class),
			@ApiResponse(code = 403, message = "Forbidden",response = ResultCode.class),
			@ApiResponse(code = 404, message = "Not Found",response = ResultCode.class)
	})
		public ResponseEntity<ResultCode> findDivisors(
			@ApiParam(value="The number provided by the user",required=true)
			@PathVariable int numberValue,
			@ApiParam(value="Whether the results should contain even or odd numbers", required =false)
			@RequestParam(required = false,name = "evenOrOdd") EvenOrOdd evenOrOdd,
			HttpServletRequest request			
			) {

		String evenOrOddType=null;
		String requestId=UUID.randomUUID().toString();		
		request.setAttribute("requestId",requestId);
		ResultCode resultCode=new ResultCode();
		if(evenOrOdd!=null)
			evenOrOddType=evenOrOdd.toString();
		LOGGER.info(requestId +" Inside "+ CLASSNAME +"::findDivisors method numberValue:"+numberValue+" evenOrOddType:"+evenOrOddType);
		try {
			if(numberValue < 1) {
				throw new NotPositiveIntegerException();
			}
			resultCode=service.findDivisors(numberValue, evenOrOddType);			
			resultCode.setRequestID(requestId);
			resultCode.setCode("Success Response");
			LOGGER.info("Obtained Divisors for numberValue:"+numberValue+" result is:"+resultCode);
			
		}catch(NumberFormatException ex) {
			LOGGER.error(requestId +" Exception while calling the service class "+ CLASSNAME);
			ex.printStackTrace();
		}
		
		return new ResponseEntity<>(resultCode,HttpStatus.OK);
		
	}

	
}
