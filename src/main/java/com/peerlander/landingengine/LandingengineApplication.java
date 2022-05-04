package com.peerlander.landingengine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peerlander.landingengine.domain.model.Balance;
import com.peerlander.landingengine.domain.model.Currency;
import com.peerlander.landingengine.domain.model.Money;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.UserRepo;

@SpringBootApplication
public class LandingengineApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(LandingengineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User jilani=new User("jilani", "jilani", "abid", 26, "software architect",new Balance());
		User adem=new User("adem", "adem", "smida", 41, "software dev",new Balance());
		jilani.topUp(new Money(100,Currency.USD));
		adem.topUp(new Money(100,Currency.USD));
		userRepo.save(jilani);
		userRepo.save(adem);
		
		userRepo.save(new User("slah", "slah", "farzit", 50, "singer",new Balance()));
		userRepo.save(new User("monji", "monji", "ouni", 60, "artist",new Balance()));
	}

}
