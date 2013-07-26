package com.example.guice.interfaces;


public interface CreditCardProcessor {
	boolean chargeCard(int amount, CreditCard creditCard);
}
