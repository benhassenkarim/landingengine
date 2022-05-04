package com.peerlander.landingengine.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.peerlander.landingengine.domain.exception.LoanApplicationNotFoundException;
import com.peerlander.landingengine.domain.exception.UserNotFoundException;
import com.peerlander.landingengine.domain.exception.loanNotFoudException;
import com.peerlander.landingengine.domain.model.Loan;
import com.peerlander.landingengine.domain.model.LoanApplication;
import com.peerlander.landingengine.domain.model.Money;
import com.peerlander.landingengine.domain.model.Status;
import com.peerlander.landingengine.domain.model.User;
import com.peerlander.landingengine.domain.repository.LoanApplicationRepo;
import com.peerlander.landingengine.domain.repository.LoanRepo;
import com.peerlander.landingengine.domain.repository.UserRepo;

@Component
public class LoanService {

	private final LoanApplicationRepo loanApplicationRepo;
	private final LoanRepo loanRepo;
	private final UserRepo userRepo;
	public LoanService(LoanApplicationRepo loanApplicationRepo, LoanRepo loanRepo, UserRepo userRepo) {
		
		this.loanApplicationRepo = loanApplicationRepo;
		this.loanRepo = loanRepo;
		this.userRepo = userRepo;
	}
	@Transactional
	public void repayLoan(final Money amountToRepay,
			    final long loanId,
			    final User borrower) {
		Loan loan=loanRepo.findOneByIdAndBorrower(loanId, borrower)
				.orElseThrow(loanNotFoudException::new);
		Money actualPaidAmount=amountToRepay.getAmount()>loan.getAmountOwed().getAmount()?
				loan.getAmountOwed():amountToRepay;
		loan.repay(actualPaidAmount);
		
	}
	@Transactional
	public void acceptLoan(final long loanApplicationId,final String  lenderUserName) {
		User lender=findUser(lenderUserName);
		LoanApplication loanApplication= findLoanApplication(loanApplicationId);
		
		loanRepo.save(loanApplication.acceptLoanApplication(lender));
	}
	public List<Loan> findAllBorrowedLoans(final User borrower,final Status status){
		return loanRepo.findAllByBorrowerAndStatus(borrower,status);
	}
	public List<Loan> findAllLentLoans(final User lender,final Status status){
		return loanRepo.findAllByLenderAndStatus(lender,status);
	}
	
	
	
	private LoanApplication findLoanApplication(final long loanApplicationId) {
		return loanApplicationRepo.findById(loanApplicationId)
				.orElseThrow(()->new LoanApplicationNotFoundException(loanApplicationId));
	}
	
	private User findUser(final String lenderUserName) {
		return userRepo.findById(lenderUserName)
				.orElseThrow(()-> new UserNotFoundException(lenderUserName));
	}
	public List<Loan> getLoans(){
		return loanRepo.findAll();
	}
}
