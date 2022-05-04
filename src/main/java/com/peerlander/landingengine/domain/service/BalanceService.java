package com.peerlander.landingengine.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peerlander.landingengine.domain.exception.UserNotFoundException;
import com.peerlander.landingengine.domain.model.Money;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.UserRepo;

@Component
public class BalanceService {

	private final UserRepo userRepo;
	
	@Autowired
	public BalanceService(UserRepo userRepo ) {
		this.userRepo=userRepo;
	}
	
	@Transactional
	public void topUpBalance(final Money money,String authToken) {
		User user=findUser(authToken);
		user.topUp(money);
	}
	
	@Transactional
	public void withdrawFromBalance(final Money money,String authToken ) {
		User user=findUser(authToken);
		user.withDraw(money);
	}
	
	private User findUser(String authToken) {
		return userRepo.findById(authToken)
				.orElseThrow(()-> new UserNotFoundException(authToken));
	}
}
