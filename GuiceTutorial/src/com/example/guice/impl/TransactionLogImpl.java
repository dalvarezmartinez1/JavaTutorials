package com.example.guice.impl;

import com.example.guice.interfaces.CreditCard;
import com.example.guice.interfaces.TransactionLog;

public class TransactionLogImpl implements TransactionLog {

	@Override
	public void logTransaction(int amount, CreditCard creditCard) {
		System.out.println(String.format("Logging transaction for card %d for the amount %d", creditCard.getNumber(), amount));		
	}

}
