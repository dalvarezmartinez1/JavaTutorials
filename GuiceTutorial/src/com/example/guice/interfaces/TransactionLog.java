package com.example.guice.interfaces;


public interface TransactionLog {
	void logTransaction(int amount, CreditCard creditCard);
}
