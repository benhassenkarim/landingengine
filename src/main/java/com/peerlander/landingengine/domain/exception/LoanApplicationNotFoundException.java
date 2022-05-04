package com.peerlander.landingengine.domain.exception;

public class LoanApplicationNotFoundException extends RuntimeException {

	public LoanApplicationNotFoundException(long loanApplicationId) {
		super("Loan Application with id : "+loanApplicationId+" Not Found");
		
	}

}
