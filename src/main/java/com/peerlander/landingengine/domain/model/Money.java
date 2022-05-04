package com.peerlander.landingengine.domain.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class Money {
	public static final  Money ZERO=new Money(0,Currency.USD);
	@Id @GeneratedValue
	private long id;
	private  Currency currency;
	private  double amount;
	
	
	
	public Money (double amount,Currency currency) {
		this.currency=currency;
		this.amount= amount;//BigDecimal.valueOf(amount).setScale(2);
	}
	
	public Money() {}
	public Money times(double multiplier) {
		return new Money(amount*multiplier,currency);
	}
	public Money add(final Money money) {
		if(currency !=money.getCurrency()) {
			throw new IllegalArgumentException(); 
		}
		return new Money(amount+money.getAmount(),currency);
	}
	public Money minus(final Money money) {
		if(currency != money.getCurrency() || amount < money.getAmount()) {
			throw new IllegalArgumentException();
		}
		return new Money(amount-money.getAmount(),currency);
	}

	public Currency getCurrency() {
		return currency;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Money other = (Money) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (currency != other.currency)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Money [id=%s, currency=%s, amount=%s]", id, currency, amount);
	}

	

	

	

	

	

	

	
	
   
}
