package com.example.guice;

import com.example.guice.impl.CreditCardProcessorImpl;
import com.example.guice.impl.TransactionLogImpl;
import com.example.guice.interfaces.CreditCardProcessor;
import com.example.guice.interfaces.TransactionLog;
import com.google.inject.AbstractModule;

/*
 * Guice uses bindings to map types to their implementations. 
 * A module is a collection of bindings specified using fluent, 
 * English-like method calls
 */

public class BillingModule extends AbstractModule {
	@Override
	protected void configure() {
		/*
		 * This tells Guice that whenever it sees a dependency on a
		 * TransactionLog, it should satisfy the dependency using a
		 * TransactionLogImplLog.
		 */
		bind(TransactionLog.class).to(TransactionLogImpl.class);

		/*
		 * Similarly, this binding tells Guice that when CreditCardProcessor is
		 * used in a dependency, that should be satisfied with a
		 * PaypalCreditCardProcessor.
		 */
		bind(CreditCardProcessor.class).to(CreditCardProcessorImpl.class);

		/*
		 * Aditionally you can also bind to Factories, not only class to class
		 */
	}
}
