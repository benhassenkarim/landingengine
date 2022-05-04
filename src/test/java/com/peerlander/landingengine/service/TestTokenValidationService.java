package com.peerlander.landingengine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.peerlander.landingengine.application.service.TokenValidationService;
import com.peerlander.landingengine.domain.exception.UserNotFoundException;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.UserRepo;
@Profile("test")
@Primary
@Component
public class TestTokenValidationService implements TokenValidationService {

	private final UserRepo userRepo;
	
	@Autowired
	public TestTokenValidationService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}


	@Override
	public User validateTokenAndGetUser(String token) {
		
		return userRepo.findById(token).orElseThrow(()-> new UserNotFoundException(token));
	}

}
