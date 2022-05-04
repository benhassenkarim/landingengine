package com.peerlander.landingengine.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peerlander.landingengine.domain.model.Loan;
import com.peerlander.landingengine.domain.model.Status;
import com.peerlander.landingengine.domain.model.User;
@Repository
public interface LoanRepo extends JpaRepository<Loan, Long>{


	List<Loan> findAllByBorrowerAndStatus(User borrower,Status status);
	List<Loan> findAllByLenderAndStatus(User lender,Status status);
	Optional<Loan> findOneByIdAndBorrower(Long id,User borrower);
}
