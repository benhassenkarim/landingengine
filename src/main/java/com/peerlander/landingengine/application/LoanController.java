package com.peerlander.landingengine.application;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.peerlander.landingengine.application.model.LoanRepaymentRequest;
import com.peerlander.landingengine.application.model.LoanRequest;
import com.peerlander.landingengine.application.service.TokenValidationService;
import com.peerlander.landingengine.application.service.ipml.TokenValidationServiceimpl;
import com.peerlander.landingengine.domain.model.Loan;
import com.peerlander.landingengine.domain.model.LoanApplication;
import com.peerlander.landingengine.domain.model.Status;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.LoanApplicationRepo;

import com.peerlander.landingengine.domain.service.LoanApplicationAdapter;
import com.peerlander.landingengine.domain.service.LoanService;

@RestController
public class LoanController {

	private final LoanApplicationRepo loanApplicationRepo;
	
	private final LoanApplicationAdapter loanApplicatiocAdapter;
	private final LoanService LoanService;
	private final TokenValidationService tokenValidationService;
	@Autowired
	public LoanController(LoanApplicationRepo loanApplicationRepo,
			LoanApplicationAdapter loanApplicatiocAdapter, LoanService LoanService
			,TokenValidationServiceimpl tokenValidationService) {
		this.loanApplicationRepo=loanApplicationRepo;
		
		this.loanApplicatiocAdapter = loanApplicatiocAdapter;
		this.LoanService = LoanService;
		this.tokenValidationService=tokenValidationService;
	}
	
	@PostMapping(value = "/loan/request")
	public void requestLoan(@RequestBody final LoanRequest loanRequest,HttpServletRequest request) {
		User borrower=tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		loanApplicationRepo.save(loanApplicatiocAdapter.transform(loanRequest,borrower));
	}

	@GetMapping(value = "/loan/requests")
	public List<LoanApplication> findAllLoanAplication(HttpServletRequest request ){
		tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		return loanApplicationRepo.findAllByStatusEquals(Status.ONGOING);
	}
	@GetMapping(value = "/loan/{status}/borrowed")
	public List<Loan> findBorrowedLoans(@RequestHeader String authorization,
			                            @PathVariable Status status){
		User Borrower = tokenValidationService.validateTokenAndGetUser(authorization);
		return LoanService.findAllBorrowedLoans(Borrower,status);
	}
	@GetMapping(value = "/loan/{status}/lent")
	public List<Loan> findLentLoans(@RequestHeader String authorization,
			                        @PathVariable Status status){
		User lender = tokenValidationService.validateTokenAndGetUser(authorization);
		return LoanService.findAllLentLoans(lender,status);
	}
	@PostMapping(value="/loan/repay")
	public void repayLoan(@RequestBody LoanRepaymentRequest request,
			@RequestHeader String authorization) {
		User borrower=tokenValidationService.validateTokenAndGetUser(authorization);
		LoanService.repayLoan(request.getAmount(), request.getLoanId(), borrower);
	}
	@PostMapping(value = "/loan/accept/{loanApplicationId}")
	public void acceptLoan(
			@PathVariable final String loanApplicationId,
			HttpServletRequest request){
		
		User lander=tokenValidationService
				.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		LoanService.acceptLoan(Long.parseLong(loanApplicationId), lander.getUsername());
	}
	@GetMapping(value = "/loans")
	public List<Loan> getLoans(){
		return LoanService.getLoans();
	}
}
