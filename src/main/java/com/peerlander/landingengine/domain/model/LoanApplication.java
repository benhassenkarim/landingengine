package com.peerlander.landingengine.domain.model;

import java.time.Duration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public final class LoanApplication {

	
	@Id@GeneratedValue
	private long id;
	private  int amount;
	@ManyToOne
	private  User borrower;
	private  int repaymentTerm;
	private  double interrestRate;
	private Status status;
	
	public LoanApplication() {}
	public LoanApplication(int amount, User borrower, int repaymentTerm, double interrestRate) {
		
		this.amount = amount;
		this.borrower = borrower;
		this.repaymentTerm = repaymentTerm;
		this.interrestRate = interrestRate;
		this.status=Status.ONGOING;
	}
	
	public Loan acceptLoanApplication(final User lender) {
		lender.withDraw(getAmount());
		borrower.topUp(getAmount());
		status=Status.COMPLETED;
		return new Loan(lender,this);
	}
	
	public long getId() {
		return id;
	}
	public Money getAmount() {
		return new Money(amount,Currency.USD);
	}

	public User getBorrower() {
		return borrower;
	}

	public int getRepaymentTerm() {
		return repaymentTerm;
	}

	public double getInterrestRate() {
		return interrestRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((borrower == null) ? 0 : borrower.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(interrestRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + repaymentTerm;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanApplication other = (LoanApplication) obj;
		if (amount != other.amount)
			return false;
		if (borrower == null) {
			if (other.borrower != null)
				return false;
		} else if (!borrower.equals(other.borrower))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(interrestRate) != Double.doubleToLongBits(other.interrestRate))
			return false;
		if (repaymentTerm != other.repaymentTerm)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("LoanRequest [amount=%s, borrower=%s, repaymentTerm=%s, interrestRate=%s]", amount,
				borrower, repaymentTerm, interrestRate);
	}
	
	
	
}
