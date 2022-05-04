package com.peerlander.landingengine.application.service.ipml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.peerlander.landingengine.application.service.TokenValidationService;
import com.peerlander.landingengine.domain.exception.UserNotFoundException;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.UserRepo;

@Component
public class TokenValidationServiceimpl implements TokenValidationService{

	private final UserRepo userRepo;
	private final RestTemplate restTemplate=new RestTemplate();
	private final String securityContextBaseUrl;
	@Autowired
	public TokenValidationServiceimpl(final UserRepo userRepo,
			@Value("${security.baseurl}")final String securityContextBaseUrl) {
		this.userRepo=userRepo;
		this.securityContextBaseUrl=securityContextBaseUrl;
	}
	
	public User validateTokenAndGetUser(final String token) {
		HttpHeaders httpHeader=new HttpHeaders();
		httpHeader.add(HttpHeaders.AUTHORIZATION, token);
		HttpEntity httpEntity=new HttpEntity(httpHeader);
		
		ResponseEntity<String> response=restTemplate
				.exchange(securityContextBaseUrl+"/user/validate", 
						HttpMethod.POST,httpEntity,String.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
		return userRepo.findById(response.getBody())
				.orElseThrow(()->new UserNotFoundException(response.getBody()));	
		}
		throw new RuntimeException("invalide token");
		
	}
}
