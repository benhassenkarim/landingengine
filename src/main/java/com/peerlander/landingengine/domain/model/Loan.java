package com.peerlander.landingengine.domain.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Loan {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private User borrower;
	@ManyToOne
	private User lender;
	@OneToOne(cascade = CascadeType.ALL)
	private Money loanAmount;
	private double interestRate;
	private LocalDate dateLent;
	private LocalDate dateDue;
	@OneToOne(cascade = CascadeType.ALL)
	private Money amountRepayed;
	private Status status;
	
	public Loan() {}
	public Loan (User lender,LoanApplication loanApplivation) {
		this.borrower=loanApplivation.getBorrower();
		this.lender=lender;
		this.loanAmount=loanApplivation.getAmount();
		this.interestRate=loanApplivation.getInterrestRate();
		this.dateLent=LocalDate.now();
		this.dateDue=LocalDate.now().plusDays(loanApplivation.getRepaymentTerm());
		this.amountRepayed=Money.ZERO;
		this.status=Status.ONGOING;
	}
	public void repay(final Money money) {
		borrower.withDraw(money);
		lender.topUp(money);
		amountRepayed=amountRepayed.add(money);
		
		if(getAmountOwed().equals(money.ZERO)) {
			status=Status.COMPLETED;
		}
	}
	public Money getAmountOwed() {
		return loanAmount.times(1+interestRate/100d).minus(amountRepayed);
	}
	public long getId() {
		return id;
	}
	public User getBorrower() {
		return borrower;
	}
	public User getLender() {
		return lender;
	}
	public Money getAmountRepayed() {
		return amountRepayed;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountRepayed == null) ? 0 : amountRepayed.hashCode());
		result = prime * result + ((borrower == null) ? 0 : borrower.hashCode());
		result = prime * result + ((dateDue == null) ? 0 : dateDue.hashCode());
		result = prime * result + ((dateLent == null) ? 0 : dateLent.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(interestRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lender == null) ? 0 : lender.hashCode());
		result = prime * result + ((loanAmount == null) ? 0 : loanAmount.hashCode());
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
		Loan other = (Loan) obj;
		if (amountRepayed == null) {
			if (other.amountRepayed != null)
				return false;
		} else if (!amountRepayed.equals(other.amountRepayed))
			return false;
		if (borrower == null) {
			if (other.borrower != null)
				return false;
		} else if (!borrower.equals(other.borrower))
			return false;
		if (dateDue == null) {
			if (other.dateDue != null)
				return false;
		} else if (!dateDue.equals(other.dateDue))
			return false;
		if (dateLent == null) {
			if (other.dateLent != null)
				return false;
		} else if (!dateLent.equals(other.dateLent))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(interestRate) != Double.doubleToLongBits(other.interestRate))
			return false;
		if (lender == null) {
			if (other.lender != null)
				return false;
		} else if (!lender.equals(other.lender))
			return false;
		if (loanAmount == null) {
			if (other.loanAmount != null)
				return false;
		} else if (!loanAmount.equals(other.loanAmount))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format(
				"Loan [id=%s, borrower=%s, lender=%s, loanAmount=%s, interestRate=%s, dateLent=%s, dateDue=%s, amountRepayed=%s]",
				id, borrower, lender, loanAmount, interestRate, dateLent, dateDue, amountRepayed);
	}


}
