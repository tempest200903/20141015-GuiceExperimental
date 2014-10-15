package com.github.tempest200903.example101;

class ProductC implements AbstractC {

	private ProductD d = new ProductD();

	ProductC() {
	}

	public AbstractD getAbstractD() {
		return d;
	}

}
