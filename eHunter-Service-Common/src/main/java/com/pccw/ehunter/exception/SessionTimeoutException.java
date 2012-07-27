package com.pccw.ehunter.exception;

public class SessionTimeoutException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SessionTimeoutException(String message){
		super(message);
	}

}
