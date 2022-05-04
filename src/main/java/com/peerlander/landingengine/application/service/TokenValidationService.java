package com.peerlander.landingengine.application.service;

import com.peerlander.landingengine.domain.model.User;

public interface TokenValidationService {
	public User validateTokenAndGetUser(final String token);
}
