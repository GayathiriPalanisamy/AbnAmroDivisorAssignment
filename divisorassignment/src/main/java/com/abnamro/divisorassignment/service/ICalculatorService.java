package com.abnamro.divisorassignment.service;

import com.abnamro.divisorassignment.bean.ResultCode;

public interface ICalculatorService {
	
	public ResultCode findDivisors(int numberValue, String evenOrOdd);

}
