package com.example.guice.impl;

import com.example.guice.interfaces.CreditCard;
import com.example.guice.interfaces.CreditCardProcessor;

public class CreditCardProcessorImpl implements CreditCardProcessor {

	@Override
	public boolean chargeCard(int amount, CreditCard creditCard) {
		System.out.println(String.format("Charging credit card with no %d whose proprietary is %s", creditCard.getNumber(), creditCard.getName()));
		return true;
	}

}
