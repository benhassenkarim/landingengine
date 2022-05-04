package com.peerlander.landingengine.domain.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peerlander.landingengine.application.model.LoanRequest;
import com.peerlander.landingengine.domain.exception.LoanApplicationNotFoundException;
import com.peerlander.landingengine.domain.exception.UserNotFoundException;
import com.peerlander.landingengine.domain.model.LoanApplication;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.UserRepo;

@Component
public class LoanApplicationAdapter {
	private final UserRepo userRepo;
	@Autowired
	public LoanApplicationAdapter(UserRepo userRepository) {
		this.userRepo=userRepository;
	}

	public LoanApplication transform(LoanRequest req,User borrower) {
		Optional<User> user=userRepo.findById(borrower.getUsername());
		if(user.isPresent()) {
			return new LoanApplication(req.getAmount(), user.get(),
					req.getDaysToRepay(), req.getInterrestRate());
		}
		else {
		 	throw new UserNotFoundException(borrower.getUsername());
		}
	}
}
