package com.peerlander.landingengine.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peerlander.landingengine.domain.model.LoanApplication;
import com.peerlander.landingengine.domain.model.Status;

@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanApplication, Long>{
	List<LoanApplication> findAllByStatusEquals(Status status);

}
