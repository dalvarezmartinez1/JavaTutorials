package com.example.guice.impl;

import javax.inject.Inject;

import com.example.guice.interfaces.BillingService;
import com.example.guice.interfaces.CreditCard;
import com.example.guice.interfaces.CreditCardProcessor;
import com.example.guice.interfaces.PizzaOrder;
import com.example.guice.interfaces.Receipt;
import com.example.guice.interfaces.TransactionLog;

public class BillingServiceImpl implements BillingService {
	private final CreditCardProcessor processor;
	private final TransactionLog transactionLog;

	@Inject
	BillingServiceImpl(CreditCardProcessor processor,
			TransactionLog transactionLog) {
		this.processor = processor;
		this.transactionLog = transactionLog;
	}

	@Override
	  public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
		processor.chargeCard(order.getPrice(), creditCard);
		transactionLog.logTransaction(order.getPrice(), creditCard);
		return new ReceiptImpl(1, order.getPrice());
	  }
}
