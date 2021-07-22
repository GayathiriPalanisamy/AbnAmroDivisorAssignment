package com.abnamro.divisorassignment.bean;

import java.util.Arrays;

import io.swagger.annotations.ApiModel;


public class NumberArrayResult {

	private int[] numberArray;

	public NumberArrayResult() {
		super();
	}

	public int[] getNumberArray() {
		return numberArray;
	}

	public void setNumberArray(int[] numberArray) {
		this.numberArray = numberArray;
	}

	@Override
	public String toString() {
		return "NumberArrayResult [numberArray=" + Arrays.toString(numberArray) + "]";
	}
	
}
