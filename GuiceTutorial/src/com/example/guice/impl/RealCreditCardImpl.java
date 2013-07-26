package com.example.guice.impl;

import java.util.Date;

import com.example.guice.interfaces.CreditCard;

public class RealCreditCardImpl implements CreditCard {
	String name;
	Date expirationDate;
	int number;
	int csv;
	
	public RealCreditCardImpl(String name, Date expirationDate, int creditCardNo, int csv) {
		this.name = name;
		this.expirationDate = expirationDate;
		this.number = creditCardNo;
		this.csv = csv;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public Date getExpirationDate() {
		return expirationDate;
	}

	@Override
	public int getCSV() {
		return csv;
	}

}
