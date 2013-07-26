package com.example.guice.impl;

import com.example.guice.interfaces.PizzaOrder;

public class PizzaOrderImpl implements PizzaOrder {

	@Override
	public int getPrice() {
		return 20;
	}

}
