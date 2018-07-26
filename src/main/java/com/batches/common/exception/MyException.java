package com.batches.common.exception;

public class MyException extends RuntimeException{
	
	public MyException(String msg , Exception e) {
		super(msg,e);
	}

	public MyException(String msg) {
		super(msg);
	}
}
