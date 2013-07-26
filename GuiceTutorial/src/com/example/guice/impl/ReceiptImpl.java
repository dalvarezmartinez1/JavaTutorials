package com.example.guice.impl;

import com.example.guice.interfaces.Receipt;

public class ReceiptImpl implements Receipt {
	int no;
	int price;

	public ReceiptImpl(int no, int price) {
		this.no = no;
		this.price = price;
	}

	@Override
	public int getNo() {
		return no;
	}

	@Override
	public int getPrice() {
		return price;
	}
}
