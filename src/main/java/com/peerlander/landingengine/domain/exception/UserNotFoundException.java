package com.peerlander.landingengine.domain.exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String username) {
		super("User whith Id : "+username+" not found");
		
	}

}
