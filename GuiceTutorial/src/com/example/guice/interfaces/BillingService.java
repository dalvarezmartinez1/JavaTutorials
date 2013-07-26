package com.example.guice.interfaces;


public interface BillingService {
	Receipt chargeOrder(PizzaOrder pizzaOrder, CreditCard creditCard);
}
