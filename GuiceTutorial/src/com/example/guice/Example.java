package com.example.guice;

import java.util.Date;

import com.example.guice.impl.BillingServiceImpl;
import com.example.guice.impl.FakeCreditCardImpl;
import com.example.guice.impl.PizzaOrderImpl;
import com.example.guice.impl.RealCreditCardImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Example {
	//https://code.google.com/p/google-guice/wiki/GettingStarted
	public static void main(String[] args) {
		// Tell the injector how the graph object will be created
		Injector injector = Guice.createInjector(new BillingModule());
		
		//Example of real usage
		BillingServiceImpl realBillingService = injector.getInstance(BillingServiceImpl.class);
		RealCreditCardImpl creditCard = new RealCreditCardImpl("John Doe", new Date(), 123_456_789, 111);	
		realBillingService.chargeOrder(new PizzaOrderImpl(), creditCard);
		
		/* Now imagine a test where you want to test the chargeOrder, what would you do?
		 Well if you didn't use DI and optionally injection, you would have to create
		another billing service, like this with DI, you just pass the FakeCreditCard to chargeOrder.
		*/
		System.out.println("TESTING STARTS!");
		realBillingService.chargeOrder(new PizzaOrderImpl(), new FakeCreditCardImpl());
		/*That is it! 
		 * Remember, very important pass everything a class needs in the constructor(if possible interfaces)
		 * This is what Dependency Injection means.
		 */
	}

}
