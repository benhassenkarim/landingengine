package com.peerlander.landingengine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peerlander.landingengine.domain.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, String>{

}
