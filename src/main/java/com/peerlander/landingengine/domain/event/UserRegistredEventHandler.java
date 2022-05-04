package com.peerlander.landingengine.domain.event;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.UserRepo;

@Component
public class UserRegistredEventHandler {
	
    private Logger LOGGER=LoggerFactory.getLogger(UserRegistredEventHandler.class);
    private static final Gson GSON=new Gson();
    private final UserRepo userRepo;
    @Autowired
    public UserRegistredEventHandler (UserRepo userRepo) {
    	this.userRepo= userRepo;
    }
    public void handelUserRegistration(String userDetails) {
    	User user=GSON.fromJson(userDetails, User.class);
    	LOGGER.info("user {} registred in landingengine",user.getUsername());
    	userRepo.save(user);
    }
    
}
