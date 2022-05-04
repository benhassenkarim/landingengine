package com.peerlander.landingengine.application.model;

import java.time.Duration;

import com.peerlander.landingengine.domain.model.User;

public class LoanRequest {
	private final int amount;
	
	private final int  daysToRepay;
	private final double interrestRate;
	public LoanRequest(int amount, int daysToRepay, double interrestRate) {
		super();
		this.amount = amount;
		
		this.daysToRepay = daysToRepay;
		this.interrestRate = interrestRate;
	}
	public int getAmount() {
		return amount;
	}
	
	public int getDaysToRepay() {
		return daysToRepay;
	}
	public double getInterrestRate() {
		return interrestRate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + daysToRepay;
		long temp;
		temp = Double.doubleToLongBits(interrestRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		LoanRequest other = (LoanRequest) obj;
		if (amount != other.amount)
			return false;
		if (daysToRepay != other.daysToRepay)
			return false;
		if (Double.doubleToLongBits(interrestRate) != Double.doubleToLongBits(other.interrestRate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("LoanRequest [amount=%s, daysToRepay=%s, interrestRate=%s]", amount, daysToRepay,
				interrestRate);
	}
	
	
	
	
}
