package com.peerlander.landingengine.application.model;

import com.peerlander.landingengine.domain.model.Currency;
import com.peerlander.landingengine.domain.model.Money;

public final  class LoanRepaymentRequest {

	private final double amount;
	private final long loanId;
	public LoanRepaymentRequest(double amount, long loanId) {
		
		this.amount = amount;
		this.loanId = loanId;
	}
	public Money getAmount() {
		return new Money(amount,Currency.USD);
	}
	public long getLoanId() {
		return loanId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (loanId ^ (loanId >>> 32));
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
		LoanRepaymentRequest other = (LoanRepaymentRequest) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (loanId != other.loanId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("LoanRepaymentRequest [amount=%s, loanId=%s]", amount, loanId);
	}
	
	
}
