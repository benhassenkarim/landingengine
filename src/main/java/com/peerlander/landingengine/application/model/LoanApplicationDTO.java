package com.peerlander.landingengine.application.model;

import com.peerlander.landingengine.domain.model.Money;
import com.peerlander.landingengine.domain.model.User;

public class LoanApplicationDTO {

	private long id;
	private Money money;
	public void setId(long id) {
		this.id = id;
	}
	public void setMoney(Money money) {
		this.money = money;
	}
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}
	public void setRepaymentDays(int repaymentDays) {
		this.repaymentDays = repaymentDays;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	private User borrower;
	private int repaymentDays;
	private double interestRate;
	
	public long getId() {
		return id;
	}
	public Money getMoney() {
		return money;
	}
	
	public User getBorrower() {
		return borrower;
	}
	public int getRepaymentDays() {
		return repaymentDays;
	}
	public double getInterestRate() {
		return interestRate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((borrower == null) ? 0 : borrower.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(interestRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + repaymentDays;
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
		LoanApplicationDTO other = (LoanApplicationDTO) obj;
		if (borrower == null) {
			if (other.borrower != null)
				return false;
		} else if (!borrower.equals(other.borrower))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(interestRate) != Double.doubleToLongBits(other.interestRate))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (repaymentDays != other.repaymentDays)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("LoanApplicationDTO [id=%s, money=%s, borrower=%s, repaymentDays=%s, interestRate=%s]", id,
				money, borrower, repaymentDays, interestRate);
	}
	
	
	
	
	
}
