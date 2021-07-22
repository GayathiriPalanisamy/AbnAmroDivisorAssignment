package com.abnamro.divisorassignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.RequestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.request.WebRequest;

import com.abnamro.divisorassignment.bean.NumberArrayResult;
import com.abnamro.divisorassignment.bean.ResultCode;

import io.micrometer.core.ipc.http.HttpSender.Request;

@Service
@Component
public class CalculatorService implements ICalculatorService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);
	@Override
	public ResultCode findDivisors(int numberValue, String evenOrOdd) {
		// TODO Auto-generated method stub
		HttpHeaders requestHeader=new HttpHeaders();
		LOGGER.info("Inside CalculatorService::findDivisors method");
		ResultCode resultCode=new ResultCode();
		List<Integer> calcualtedResult = new ArrayList<>();
		calcualtedResult.add(1); //since all the number are divided by 1
		NumberArrayResult number=new NumberArrayResult();
		try
		{
			for(int i=2;i<=10;i++) //i<=(numberValue/2)
			{			
				if(numberValue%i == 0) 
				{   
					if(evenOrOdd == null) {
						calcualtedResult.add(i);
					}else {						
						if(evenOrOdd.equalsIgnoreCase("even")) {
							if(i%2==0)
								calcualtedResult.add(i);
						}else{
							if(i%2 != 0)
								calcualtedResult.add(i);
					}
				}
			}								
			}
			int[] result=calcualtedResult.stream().mapToInt(Integer::intValue).toArray();
			LOGGER.info("Obtained the divisors of the given number is :" + calcualtedResult);
			number.setNumberArray(result);
			resultCode.setMessage(number.toString());
			
		}catch(NumberFormatException ex) {
			LOGGER.error("Exception while calculating the divisor for the numberValue");
			ex.printStackTrace();
		}catch (Exception e) { 
				LOGGER.error("Exception while calculating the divisor for the numberValue");
				e.printStackTrace();
		} 
		return resultCode;
	}

}
