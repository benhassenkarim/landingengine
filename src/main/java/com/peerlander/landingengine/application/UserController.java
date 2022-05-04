package com.peerlander.landingengine.application;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peerlander.landingengine.application.service.TokenValidationService;
import com.peerlander.landingengine.application.service.ipml.TokenValidationServiceimpl;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.UserRepo;
@RestController
public class UserController {
	private final UserRepo userRepo;
	private final TokenValidationService tokenValidationService;
	@Autowired
	public UserController(UserRepo userRepo, TokenValidationService tokenValidationService) {
		
		this.userRepo = userRepo;
		this.tokenValidationService = tokenValidationService;
	}
	@GetMapping(value = "/users")
	public List<User> findUsers(HttpServletRequest request){
		tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		return userRepo.findAll();
	}
}
